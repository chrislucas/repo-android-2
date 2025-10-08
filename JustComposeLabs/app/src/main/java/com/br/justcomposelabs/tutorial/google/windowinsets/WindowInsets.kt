package com.br.justcomposelabs.tutorial.google.windowinsets

/*

    https://developer.android.com/develop/ui/compose/system/insets

    Compose and Window Insets
    https://developer.android.com/develop/ui/compose/system/insets
    https://developer.android.com/develop/ui/compose/system/insets-ui

    Compose layout adjust layout below edges


    Adjusting a Jetpack Compose layout "below edges" typically refers to handling
    window insets to ensure content is not obscured by system UI elements like the status bar,
    navigation bar, or display cutouts, especially when implementing an edge-to-edge design.

    Key concepts for adjusting layouts below edges:

    Window Insets:
    These represent areas of the screen that are occupied by system UI. Compose provides APIs to access and utilize these insets to adjust your layout.
    WindowInsets.safeDrawing: Represents the area where interactive elements should be placed to avoid overlapping with system UI.
    WindowInsets.systemBars: Represents the space occupied by the status bar and navigation bar.
    WindowInsets.displayCutout: Represents the area of display cutouts (notches, punch-holes).

    Applying Insets:
    Padding Modifiers: The most common way to adjust for insets is by applying padding modifiers using the WindowInsets values. For example, Modifier.padding(WindowInsets.safeDrawing.asPaddingValues()) applies padding around the entire content to avoid system UI.
    Inset Size Modifiers: For more fine-grained control, you can use modifiers like Modifier.windowInsetsPadding() or Modifier.windowInsetsEnd() to apply padding or size adjustments based on specific inset types.

    Edge-to-Edge Design:
    When designing edge-to-edge, your app draws behind the system bars and display cutouts. This requires careful handling of insets to prevent content from being hidden.
    On Android 15 (API level 35) and higher, edge-to-edge is enforced when targeting SDK 35, requiring apps to handle insets.
    The Scaffold component from Material 3 can simplify handling insets for common UI structures.





 */