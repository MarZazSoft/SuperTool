package com.marzazsoft.mobile.notes.presentation.screens.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.marzazsoft.mobile.notes.models.Note
import com.marzazsoft.supertooldesign.utils.MEDIUM_BORDER
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.black
import com.marzazsoft.supertooldesign.utils.gray
import com.marzazsoft.supertooldesign.utils.superLightBlue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("ktlint:standard:function-naming")
@Composable
fun NoteItem(
    note: Note,
    onClickAction: () -> Unit,
) {
    NoteItemUi(
        title = note.title,
        content = note.content,
        date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(note.createdAt)),
        onClickAction = onClickAction,
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun NoteItemUi(
    title: String,
    content: String,
    date: String,
    onClickAction: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors =
            CardColors(
                containerColor = superLightBlue,
                contentColor = black,
                disabledContentColor = black,
                disabledContainerColor = superLightBlue,
            ),
        shape = RoundedCornerShape(MEDIUM_BORDER),
        onClick = { onClickAction() },
    ) {
        Column(modifier = Modifier.padding(SIMPLE_PADDING)) {
            Text(
                text = title,
                color = black,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = content,
                color = black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = date,
                color = gray,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    NoteItem(
        note = Note(title = "Mi nota", content = "Contenido de la nota", createdAt = System.currentTimeMillis()),
    ) {}
}
