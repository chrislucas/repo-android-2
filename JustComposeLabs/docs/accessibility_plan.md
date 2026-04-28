# Plano de Acessibilidade para Custom Views

Este documento descreve o plano para tornar as Custom Views do projeto `JustComposeLabs` compatíveis com as recomendações de acessibilidade do Google.

## Visão Geral

Para cada Custom View, seguiremos as diretrizes de:
1. **Descrições de Conteúdo**: Fornecer informações textuais para elementos visuais.
2. **Navegação por Teclado e Foco**: Garantir que todos os elementos interativos sejam acessíveis via D-pad ou teclado.
3. **Eventos de Acessibilidade**: Notificar serviços de acessibilidade sobre mudanças de estado.
4. **Suporte a Gestos**: Garantir que ações baseadas em gestos tenham alternativas para usuários de leitores de tela.

---

## 1. Custom Views Clássicas (Herança de `android.view.View`)

### RegularPolygonView e Derivadas
*Exemplos: `RegularPolygonView`, `HexagonView`, `TrianglePathJoinView`*

**Análise**: São views principalmente decorativas ou educacionais que desenham formas geométricas.
**Plano**:
- **Content Description**: Definir `android:contentDescription` via XML ou programaticamente descrevendo a forma (ex: "Polígono regular de 5 lados").
- **Focabilidade**: Se forem apenas ilustrativas, manter `android:focusable="false"`.
- **Anúncio de Mudança**: Se o número de lados mudar dinamicamente, chamar `announceForAccessibility("Polígono alterado para X lados")`.

**Exemplo de Código**:
```kotlin
// No init ou quando as propriedades mudarem
fun updateAccessibility() {
    contentDescription = "Polígono regular de $sides lados com estilo de junção ${join.name}"
}

// Quando houver uma mudança dinâmica (ex: via animação ou interação)
fun onSidesChanged(newSides: Int) {
    sides = newSides
    announceForAccessibility("O polígono agora possui $newSides lados")
    invalidate()
}
```

### DraggableTriangleView e Views de Geometria Interativas
*Exemplos: `DraggableTriangleView`, `ElasticDraggableTriangleView`, `SpringAnimationDraggableTriangleView`*

**Análise**: Permitem arrastar vértices para modificar a forma. Atualmente usam `onTouchEvent`.
**Plano**:
- **Ações de Acessibilidade Customizadas**: Implementar `AccessibilityNodeProvider` ou usar `ViewCompat.replaceAccessibilityAction` para permitir mover os vértices usando o menu de ações do TalkBack.
- **Clique e Foco**: Já possui `performClick()`, mas precisa garantir que o foco percorra cada vértice individualmente se possível, ou forneça uma interface alternativa para ajuste (ex: sliders de coordenadas).
- **Feedback Auditivo**: Emitir `AccessibilityEvent.TYPE_VIEW_SELECTED` quando um vértice for capturado.

**Exemplo de Código**:
```kotlin
// Implementando suporte para ações do TalkBack nos vértices
init {
    ViewCompat.setAccessibilityDelegate(this, object : AccessibilityDelegateCompat() {
        override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // Adiciona ações para mover o vértice selecionado
            info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                R.id.action_move_up, "Mover vértice para cima"
            ))
        }

        override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
            return when (action) {
                R.id.action_move_up -> {
                    vertices[selectedVertexIndex].y -= 10f
                    announceForAccessibility("Vértice movido para cima")
                    invalidate()
                    true
                }
                else -> super.performAccessibilityAction(host, action, args)
            }
        }
    })
}
```

---

## 2. Jetpack Compose Custom Views (Uso de `Canvas`)

### InteractiveUnitCircle
*Localização: `canvasviews/.../usingcomposeinview/ComposeInViewActivity.kt`*

**Análise**: Um círculo trigonométrico interativo onde o usuário arrasta para mudar o ângulo.
**Plano**:
- **Semantics Modifier**: Usar o modificador `.semantics { ... }` para descrever o estado atual (ângulo em graus).
- **ProgressBar/Slider Semantics**: Implementar `setProgress` e `stateDescription` dentro de `semantics` para que o TalkBack trate o círculo como um controle ajustável.
- **Clear e Custom Actions**: Adicionar ações customizadas para "Aumentar 1 grau" e "Diminuir 1 grau".

**Exemplo de Código**:
```kotlin
Canvas(
    modifier = Modifier
        .size(sizeDp)
        .semantics {
            contentDescription = "Círculo Unitário Trigonométrico"
            valueRange = 0f..360f
            // Trata o círculo como um slider para o TalkBack
            progressBarRangeInfo = ProgressBarRangeInfo(angleDeg.toFloat(), 0f..360f)
            stateDescription = "${angleDeg.toInt()} graus"
            
            // Permite ajustar o valor via gestos de acessibilidade (swipe up/down)
            setProgress { targetValue ->
                radians = Math.toRadians(targetValue.toDouble()).toFloat()
                true
            }
        }
        .pointerInput(Unit) { /* ... */ }
) {
    // Desenho...
}
```

### CartesianPlan3dAxis
*Localização: `Cartesian3DView.kt`*

**Análise**: Visualização 3D rotacionável via gestos.
**Plano**:
- **Role Descritiva**: Definir `role = Role.Image` no modificador de semântica.
- **Instruções de Uso**: Adicionar `onClick` ou `customActions` para resetar a visualização ou rotacionar em eixos fixos, facilitando o uso sem gestos complexos.
- **Live Region**: Usar `liveRegion = LiveRegionMode.Polite` para anunciar os ângulos de rotação X e Y quando alterados significativamente.

**Exemplo de Código**:
```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .semantics {
            role = Role.Image
            contentDescription = "Visualização 3D de eixos cartesianos"
            // Anuncia mudanças de rotação para o usuário de TalkBack
            liveRegion = LiveRegionMode.Polite
            stateDescription = "Rotação X: ${rotX.value}, Y: ${rotY.value}"
        }
        .clickable(
            onClickLabel = "Resetar visualização"
        ) {
            scope.launch {
                rotX.animateTo(0f)
                rotY.animateTo(0f)
            }
        }
) {
    Canvas(modifier = Modifier.fillMaxSize()) { /* ... */ }
}
```

---

## 3. Checklist de Implementação Geral

1. **Contraste de Cores**: Validar se as cores usadas no `Canvas` (como o verde do círculo unitário sobre o fundo branco) atendem ao ratio de 4.5:1.
2. **Touch Targets**: Garantir que áreas interativas (como os vértices do triângulo) tenham pelo menos 48dp x 48dp de área de toque virtual (mesmo que o desenho seja menor).
3. **Hierarchy**: Verificar se o `ImportantForAccessibility` está configurado corretamente para não confundir o leitor de tela com elementos puramente decorativos de fundo.
