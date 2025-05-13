package com.br.funwithjetpackcompose.tutorials.medium.permissionmanagement

/*
    https://buraaktasci.medium.com/clean-permission-management-in-android-ddbb92590e05

    TODO avaliar se vale a pena estudar esse artigo
    https://thekoolsk.medium.com/cleanest-way-to-manage-runtime-permissions-in-android-45225f46419b
 */

interface Permission {
    fun request()
    fun isGranted(): Boolean
}