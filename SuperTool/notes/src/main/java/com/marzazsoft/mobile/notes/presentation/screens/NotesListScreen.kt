package com.marzazsoft.mobile.notes.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.mobile.notes.R
import com.marzazsoft.mobile.notes.navigation.NavigationScreens
import com.marzazsoft.mobile.notes.presentation.screens.shared.NoteItem
import com.marzazsoft.mobile.notes.presentation.viewModels.NotesViewModel
import com.marzazsoft.mobile.supertool.common.utils.ApiStatus
import com.marzazsoft.supertooldesign.presentation.screens.SuperToolProgressIndicator
import com.marzazsoft.supertooldesign.utils.MEDIUM_PADDING
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_HEIGHT
import com.marzazsoft.supertooldesign.utils.blue
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.superLightBlue
import com.marzazsoft.supertooldesign.utils.white
import org.koin.androidx.compose.koinViewModel
import com.marzazsoft.supertooldesign.R as DesignR

@Suppress("ktlint:standard:function-naming")
@Composable
fun NotesListScreen(
    appNavController: NavController,
    notesController: NavController,
    modifier: Modifier,
    viewModel: NotesViewModel = koinViewModel(),
) {
    var backActionFlag by rememberSaveable { mutableStateOf(true) }
    val notesState by viewModel.notesState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }

    NotesListScreenUi(
        modifier = modifier,
        notesState = notesState,
        backAction = {
            if (backActionFlag) {
                backActionFlag = false
                notesController.popBackStack()
                appNavController.popBackStack()
            }
        },
        onNoteClick = { noteId ->
            notesController.navigate("${NavigationScreens.NoteDetailScreen.route}/$noteId")
        },
        onNewNote = {
            notesController.navigate("${NavigationScreens.NoteDetailScreen.route}/-1")
        },
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun NotesListScreenUi(
    modifier: Modifier,
    notesState: ApiStatus<List<com.marzazsoft.mobile.notes.models.Note>>,
    backAction: () -> Unit,
    onNoteClick: (Int) -> Unit,
    onNewNote: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize().background(darkBlue),
    ) {
        val (appBarId, contentId, fabId) = createRefs()

        Box(
            Modifier
                .background(darkBlue)
                .fillMaxWidth()
                .height(SMALL_HEIGHT)
                .constrainAs(appBarId) {
                    top.linkTo(parent.top, margin = SIMPLE_PADDING)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(SIMPLE_PADDING),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(DesignR.drawable.ic_back),
                    tint = white,
                    contentDescription = stringResource(DesignR.string.back_description),
                    modifier = Modifier.weight(1f).clickable { backAction() },
                )
                Text(
                    text = stringResource(R.string.module_name),
                    color = white,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(7f),
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MEDIUM_PADDING)
                    .constrainAs(contentId) {
                        top.linkTo(appBarId.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    },
        ) {
            when (notesState) {
                is ApiStatus.Loading -> SuperToolProgressIndicator()
                is ApiStatus.Success -> {
                    val notes = notesState.data
                    if (notes.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = stringResource(R.string.empty_notes),
                                color = superLightBlue,
                            )
                        }
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(SIMPLE_PADDING),
                            contentPadding = PaddingValues(vertical = SIMPLE_PADDING),
                        ) {
                            items(notes) { note ->
                                NoteItem(
                                    note = note,
                                    onClickAction = { onNoteClick(note.id) },
                                )
                            }
                        }
                    }
                }
                else -> Unit
            }
        }

        FloatingActionButton(
            onClick = { onNewNote() },
            modifier =
                Modifier.constrainAs(fabId) {
                    end.linkTo(parent.end, margin = MEDIUM_PADDING)
                    bottom.linkTo(parent.bottom, margin = MEDIUM_PADDING)
                },
            containerColor = blue,
        ) {
            Text(
                text = "+",
                color = white,
                fontSize = com.marzazsoft.supertooldesign.utils.LARGE_FONT_SIZE,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesListScreenPreview() {
    NotesListScreen(
        appNavController = rememberNavController(),
        notesController = rememberNavController(),
        modifier = Modifier.fillMaxSize(),
    )
}
