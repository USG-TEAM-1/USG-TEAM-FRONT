import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.book.BookApi
import com.example.myapplication.data.BookItem

class BookDetailViewModel(private val bookId: Int) : ViewModel() {
    private val bookApi = BookApi()
    val bookDetail: LiveData<BookItem> = bookApi.getBookDetail(bookId)
}

