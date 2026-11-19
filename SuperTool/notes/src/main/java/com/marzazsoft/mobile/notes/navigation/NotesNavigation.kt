package com.marzazsoft.mobile.notes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marzazsoft.mobile.notes.presentation.screens.NoteDetailScreen
import com.marzazsoft.mobile.notes.presentation.screens.NotesListScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun NotesNavigation(
    appNavController: NavController,
    modifier: Modifier,
) {
    val notesController = rememberNavController()
    NavHost(
        navController = notesController,
        startDestination = NavigationScreens.NotesListScreen.route,
    ) {
        composable(NavigationScreens.NotesListScreen.route) {
            NotesListScreen(
                appNavController = appNavController,
                notesController = notesController,
                modifier = modifier,
            )
        }
        composable(
            route = "${NavigationScreens.NoteDetailScreen.route}/{noteId}",
            arguments =
                listOf(
                    navArgument("noteId") { type = NavType.IntType },
                ),
        ) { backStackEntry ->
            NoteDetailScreen(
                notesController = notesController,
                noteId = backStackEntry.arguments?.getInt("noteId") ?: -1,
                modifier = modifier,
            )
        }
    }
}
