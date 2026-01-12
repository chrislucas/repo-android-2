package com.br.justcomposelabs.tutorial.composable.interoperability

/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose#androidview_in_lazy_lists


    specific AndroidView overload designed for lazy list
    @Composable
    fun <T : View> AndroidView(
        factory: (Context) -> T,
        modifier: Modifier = Modifier,
        update: (T) -> Unit,
        onReset: (T) -> Unit, // The key parameter for reuse
        onRelease: ((T) -> Unit)? = null // Optional release callback
    ) { ... }

    onReset: A non-null callback that is invoked to signal that the
    View is about to be reused with new data or state. This is mandatory to enable the View reuse mechanism.

    onRelease (optional): A callback invoked to signal that the View has
    exited the composition and will not be reused again (e.g., when the LazyColumn is cleared).
 */