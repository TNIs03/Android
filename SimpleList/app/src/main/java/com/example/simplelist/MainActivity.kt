import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Tạo một LinearLayout chính
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        // Tạo EditText, Button và các RadioButton
        val edtNumber = EditText(this).apply { hint = "Nhập số nguyên dương" }
        val btnShow = Button(this).apply { text = "Show" }
        val rbtnEven = RadioButton(this).apply { text = "Số chẵn" }
        val rbtnOdd = RadioButton(this).apply { text = "Số lẻ" }
        val rbtnPerfectSquare = RadioButton(this).apply { text = "Số chính phương" }

        // Tạo ListView và TextView
        val listView = ListView(this)
        val txtError = TextView(this).apply { visibility = View.GONE }

        // Thêm các view vào layout
        mainLayout.addView(edtNumber)
        mainLayout.addView(btnShow)
        mainLayout.addView(rbtnEven)
        mainLayout.addView(rbtnOdd)
        mainLayout.addView(rbtnPerfectSquare)
        mainLayout.addView(listView)
        mainLayout.addView(txtError)

        // Đặt layout chính làm nội dung của Activity
        setContentView(mainLayout)

        // Sự kiện nút nhấn
        btnShow.setOnClickListener {
            txtError.visibility = View.GONE
            val input = edtNumber.text.toString().trim()

            if (input.isEmpty() || input.toIntOrNull() == null || input.toInt() <= 0) {
                txtError.text = "Vui lòng nhập số nguyên dương"
                txtError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val n = input.toInt()
            val numbers = mutableListOf<Int>()

            when {
                rbtnEven.isChecked -> {
                    for (i in 0..n step 2) numbers.add(i)
                }
                rbtnOdd.isChecked -> {
                    for (i in 1..n step 2) numbers.add(i)
                }
                rbtnPerfectSquare.isChecked -> {
                    var i = 0
                    while (i * i <= n) {
                        numbers.add(i * i)
                        i++
                    }
                }
            }

            // Hiển thị kết quả trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }
}
