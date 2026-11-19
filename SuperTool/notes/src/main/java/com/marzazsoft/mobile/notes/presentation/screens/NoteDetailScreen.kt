package com.marzazsoft.mobile.notes.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.marzazsoft.mobile.notes.presentation.viewModels.NotesViewModel
import com.marzazsoft.supertooldesign.utils.MEDIUM_PADDING
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_HEIGHT
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.red
import com.marzazsoft.supertooldesign.utils.superLightBlue
import com.marzazsoft.supertooldesign.utils.white
import org.koin.androidx.compose.koinViewModel
import com.marzazsoft.supertooldesign.R as DesignR

@Suppress("ktlint:standard:function-naming")
@Composable
fun NoteDetailScreen(
    notesController: NavController,
    noteId: Int,
    modifier: Modifier,
    viewModel: NotesViewModel = koinViewModel(),
) {
    val isNewNote = noteId == -1
    val selectedNote by viewModel.selectedNote.collectAsState()
    var title by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(noteId) {
        if (!isNewNote) viewModel.loadNote(noteId)
    }

    LaunchedEffect(selectedNote) {
        selectedNote?.let {
            title = it.title
            content = it.content
        }
    }

    NoteDetailScreenUi(
        modifier = modifier,
        isNewNote = isNewNote,
        title = title,
        content = content,
        onTitleChange = { title = it },
        onContentChange = { content = it },
        backAction = { notesController.popBackStack() },
        saveAction = {
            if (isNewNote) {
                viewModel.saveNote(title, content)
            } else {
                selectedNote?.let { viewModel.updateNote(it, title, content) }
            }
            notesController.popBackStack()
        },
        deleteAction = {
            selectedNote?.let { viewModel.deleteNote(it) }
            notesController.popBackStack()
        },
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun NoteDetailScreenUi(
    modifier: Modifier,
    isNewNote: Boolean,
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    backAction: () -> Unit,
    saveAction: () -> Unit,
    deleteAction: () -> Unit,
) {
    val screenTitle = stringResource(if (isNewNote) R.string.new_note else R.string.edit_note)

    ConstraintLayout(
        modifier = modifier.fillMaxSize().background(darkBlue),
    ) {
        val (appBarId, contentId) = createRefs()

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
                    text = screenTitle,
                    color = white,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(5f),
                )
                Text(
                    text = stringResource(R.string.save_note),
                    color = superLightBlue,
                    fontWeight = FontWeight.Bold,
                    modifier =
                        Modifier
                            .weight(2f)
                            .clickable { saveAction() },
                    textAlign = TextAlign.End,
                )
            }
        }

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(MEDIUM_PADDING)
                    .constrainAs(contentId) {
                        top.linkTo(appBarId.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    },
            verticalArrangement = Arrangement.spacedBy(SIMPLE_PADDING),
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { onTitleChange(it) },
                label = { Text(stringResource(R.string.note_title_hint), color = superLightBlue) },
                singleLine = true,
                colors =
                    OutlinedTextFieldDefaults.colors(
                        focusedTextColor = white,
                        unfocusedTextColor = white,
                        focusedBorderColor = superLightBlue,
                        unfocusedBorderColor = superLightBlue,
                        cursorColor = white,
                    ),
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = content,
                onValueChange = { onContentChange(it) },
                label = { Text(stringResource(R.string.note_content_hint), color = superLightBlue) },
                minLines = 5,
                colors =
                    OutlinedTextFieldDefaults.colors(
                        focusedTextColor = white,
                        unfocusedTextColor = white,
                        focusedBorderColor = superLightBlue,
                        unfocusedBorderColor = superLightBlue,
                        cursorColor = white,
                    ),
                modifier = Modifier.fillMaxWidth(),
            )
            if (!isNewNote) {
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = { deleteAction() },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(R.string.delete_note),
                        color = red,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteDetailScreenPreview() {
    NoteDetailScreen(
        notesController = rememberNavController(),
        noteId = -1,
        modifier = Modifier.fillMaxSize(),
    )
}
