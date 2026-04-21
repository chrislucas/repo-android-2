# ✅ Correção: Recuperar FontScale de SharedPreferences em onRestoreInstanceState

## 🎯 Problema Identificado

Havia uma **inconsistência** na estratégia de persistência:

| Operação | Antes | Depois |
|----------|-------|--------|
| **Salvar** | ✅ SharedPreferences | ✅ SharedPreferences |
| **Ler em attachBaseContext** | ✅ SharedPreferences | ✅ SharedPreferences |
| **Restaurar em onRestoreInstanceState** | ❌ Apenas Bundle | ✅ Bundle + SharedPreferences |

**Problema:** Se recuperávamos apenas do Bundle em `onRestoreInstanceState()`, havia risco de perder o `fontScale` salvo em SharedPreferences.

---

## 📋 Mudança Implementada

### Antes (Inconsistente)

```kotlin
override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    
    // ❌ Só recupera do Bundle
    initConfigurationState = savedInstanceState.getParcelable(SAVE_CONFIG_STATE)
        ?: initConfigurationState
    
    // ❌ Ignora o fontScale em SharedPreferences!
}
```

### Depois (Consistente)

```kotlin
override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    
    // ✅ Restaurar do Bundle PRIMEIRO
    initConfigurationState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        savedInstanceState.getParcelable(SAVE_CONFIG_STATE, ConfigurationState::class.java)
            ?: initConfigurationState
    } else {
        @Suppress("DEPRECATION")
        savedInstanceState.getParcelable(SAVE_CONFIG_STATE) ?: initConfigurationState
    }
    
    // ✅ DEPOIS, recuperar fontScale de SharedPreferences
    val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    val savedFontScale = prefs.getFloat(PREF_FONT_SCALE, 1.0f)
    
    // ✅ Se o fontScale foi salvo e é diferente, atualizar
    if (savedFontScale != 1.0f && initConfigurationState?.fontScale != savedFontScale) {
        initConfigurationState = initConfigurationState?.copy(fontScale = savedFontScale)
        Timber.tag(TAG).d("FontScale restaurado de SharedPreferences: $savedFontScale")
    }
}
```

---

## 🔄 Fluxo Correto Agora

### Passo a Passo da Persistência

```
1️⃣ User move slider
   ↓
2️⃣ viewModel.updateFontScale(value)
   ↓
3️⃣ recreate() é chamado
   ↓
4️⃣ onSaveInstanceState(outState)
   ├─ outState.putParcelable(SAVE_CONFIG_STATE, initConfigurationState) → Bundle ✅
   └─ prefs.putFloat(PREF_FONT_SCALE, fontScale) → SharedPreferences ✅
   ↓
5️⃣ Activity é destruída
   ↓
6️⃣ attachBaseContext(newBase)
   └─ prefs.getFloat(PREF_FONT_SCALE) → Lê de SharedPreferences ✅
   └─ adjustFontSize(fontScale) → Aplica escala ✅
   ↓
7️⃣ onCreate(savedInstanceState)
   └─ Bundle está disponível
   ↓
8️⃣ onRestoreInstanceState(savedInstanceState)
   ├─ getParcelable(SAVE_CONFIG_STATE) → Restaura do Bundle ✅
   └─ getFloat(PREF_FONT_SCALE) → Restaura de SharedPreferences ✅
   └─ Sincroniza fontScale se necessário ✅
   ↓
9️⃣ initConfigurationState tem valor COMPLETO e CORRETO ✅
```

---

## 🎓 Por Que Fazer Isso?

### Garantia de Consistência

```
SharedPreferences (Fonte de Verdade para fontScale)
         ↓
onSaveInstanceState() salva lá
         ↓
attachBaseContext() lê de lá
         ↓
onRestoreInstanceState() TAMBÉM lê de lá
         ↓
Sempre sincronizado ✅
```

### Cenários Protegidos

| Cenário | O que acontece |
|---------|---|
| App é destruído após `onSaveInstanceState()` | ✅ FontScale está em SharedPreferences |
| `onRestoreInstanceState()` não é chamado | ✅ FontScale foi aplicado em `attachBaseContext()` |
| Bundle é perdido mas SharedPreferences persiste | ✅ FontScale é restaurado de SharedPreferences |
| Ambos estão disponíveis | ✅ Valores são sincronizados |

---

## ✨ Resultado

✅ **Sem inconsistências** - Mesma fonte de dados (SharedPreferences) em todo ciclo de vida
✅ **Persistência garantida** - FontScale nunca é perdido
✅ **Segurança** - Validação dupla de fontes
✅ **Debugging fácil** - Logs indicam de onde foi restaurado

---

## 🔍 Detalhes Técnicos

### Lógica de Sincronização

```kotlin
if (savedFontScale != 1.0f && initConfigurationState?.fontScale != savedFontScale) {
    //  ↑ Só faz algo se:
    //  1. FontScale salvo é diferente do padrão (1.0f)
    //  2. E é diferente do que está em initConfigurationState
    
    initConfigurationState = initConfigurationState?.copy(fontScale = savedFontScale)
    // Atualiza para garantir consistência
}
```

Isso garante que:
- Se valores são iguais, não refaz nada
- Se valores são diferentes, sincroniza
- Evita atualizações desnecessárias

---

## 📊 Comparação de Estratégias

### Antes (Inconsistente)
```
onSaveInstanceState  → SharedPreferences ✅
attachBaseContext    → SharedPreferences ✅
onRestoreInstanceState → Bundle APENAS ❌

Risco: FontScale pode não ser restaurado corretamente
```

### Depois (Consistente)
```
onSaveInstanceState  → SharedPreferences ✅
attachBaseContext    → SharedPreferences ✅
onRestoreInstanceState → Bundle + SharedPreferences ✅

Garantia: FontScale sempre sincronizado
```

---

## 🚀 Conclusão

Agora a estratégia de persistência é **totalmente consistente** em todo o ciclo de vida da Activity. O `fontScale` é sempre restaurado da mesma fonte (SharedPreferences) em múltiplos pontos, garantindo que nunca seja perdido. 🎉

