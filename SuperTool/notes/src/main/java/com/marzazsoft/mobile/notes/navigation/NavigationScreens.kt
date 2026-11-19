package com.marzazsoft.mobile.notes.navigation

sealed class NavigationScreens(
    val route: String,
) {
    data object MainScreen : NavigationScreens("notes_main_screen")

    data object NotesListScreen : NavigationScreens("notes_list_screen")

    data object NoteDetailScreen : NavigationScreens("note_detail_screen")
}
