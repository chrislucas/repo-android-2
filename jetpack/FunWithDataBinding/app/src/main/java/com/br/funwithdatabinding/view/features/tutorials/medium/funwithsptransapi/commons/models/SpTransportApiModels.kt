package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * https://medium.com/@nishargrocks007/using-retrofit-and-rxjava-in-your-android-project-8225f54df614
 *
 * TODO criar modelos relacionados a API da SpTrans
 * https://www.sptrans.com.br/desenvolvedores/api-do-olho-vivo-guia-de-referencia/documentacao-api#docApi-acesso
 * https://www.sptrans.com.br/desenvolvedores/perfil-desenvolvedor
 *
 * API DO OLHO VIVO - Guia de Referência
 *https://www.sptrans.com.br/desenvolvedores/api-do-olho-vivo-guia-de-referencia/documentacao-api/
 */

const val SP_TRANS_OLHO_VIVO_API_BASE_URL = "https://api.olhovivo.sptrans.com.br/v2.1/"

/**
 * @property identifierLineCode Código identificador da linha. Este é um código identificador único de cada linha
 * do sistema (por sentido de operação).
 * @property isCircularLine  Indica se uma linha opera no modo circular (sem um terminal secundário)
 * @property busSign  Informa a primeira parte do letreiro numérico da linha
 * @property operationMode Informa a segunda parte do letreiro numérico da linha, que indica se a linha opera nos modos:
 * BASE (10), ATENDIMENTO (21, 23, 32, 41)
 * @property lineDirection  Informa o sentido ao qual a linha atende, onde 1 significa Terminal
 * Principal para Terminal Secundário e 2 para Terminal Secundário para Terminal Principal
 * @property busSignFromMainToSecondaryTerminal
 * Informa o letreiro descritivo da linha no sentido Terminal Principal para Terminal Secundário
 * @property busSignFromSecondaryToMainTerminal
 *  Informa o letreiro descritivo da linha no sentido Terminal Secundário para Terminal Principal
 */

@Parcelize
data class SpBusLine(
    @SerializedName("cl") val identifierLineCode: Int,
    @SerializedName("lc") val isCircularLine: Boolean,
    @SerializedName("lt") val busSign: String,
    @SerializedName("tl") val operationMode: Int,
    @SerializedName("sl") val lineDirection: Int,
    @SerializedName("tp") val busSignFromMainToSecondaryTerminal: String,
    @SerializedName("ts") val busSignFromSecondaryToMainTerminal: String
): Parcelable