package dinh.example.uxuifinalproject

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import java.util.*
import dinh.example.uxuifinalproject.databinding.ActivityMainBinding
import java.util.Locale.filter


class MainActivity : AppCompatActivity() {
    lateinit var imgButton1:ImageButton
    lateinit var imgButton2:ImageButton
    lateinit var imgButton3:ImageButton
    lateinit var imgButton4:ImageButton
    lateinit var imgButton5:ImageButton
    lateinit var imgButton6:ImageButton
    lateinit var imgButton7:ImageButton
    lateinit var imgButton8:ImageButton
    lateinit var imgButton9:ImageButton
    lateinit var imgButton10:ImageButton
    lateinit var imgButton11:ImageButton
    lateinit var imgButton12:ImageButton

    lateinit var textView: TextView

    lateinit var buttons: Array<ImageButton>
    val cardBack = R.drawable.code
    var clicked = 0
    var turnOver = false
    var lastClicked = -1
    var temp: Int = 0

    private lateinit var binding:ActivityMainBinding
    lateinit var viewModel: MainViewModel

     var images: MutableList<Animal> = mutableListOf<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.lifecycleOwner = this

        imgButton1 = findViewById(R.id.imgBtn1)
        imgButton2 = findViewById(R.id.imgBtn2)
        imgButton3 = findViewById(R.id.imgBtn3)
        imgButton4 = findViewById(R.id.imgBtn4)
        imgButton5 = findViewById(R.id.imgBtn5)
        imgButton6 = findViewById(R.id.imgBtn6)
        imgButton7 = findViewById(R.id.imgBtn7)
        imgButton8 = findViewById(R.id.imgBtn8)
        imgButton9 = findViewById(R.id.imgBtn9)
        imgButton10 = findViewById(R.id.imgBtn10)
        imgButton11 = findViewById(R.id.imgBtn11)
        imgButton12 = findViewById(R.id.imgBtn12)

        textView = findViewById(R.id.textViewDes)



        images.add(Animal(1, R.drawable.camel, "${getString(R.string.camel)}"))
        images.add(Animal(2, R.drawable.coala, "${getString(R.string.coala)}"))
        images.add(Animal(3, R.drawable.fox, "${getString(R.string.fox)}"))
        images.add(Animal(4, R.drawable.lion, "${getString(R.string.lion)}"))
        images.add(Animal(5, R.drawable.monkey, "${getString(R.string.monkey)}"))
        images.add(Animal(6, R.drawable.wolf, "${getString(R.string.wolf)}"))
        images.add(Animal(7, R.drawable.camel, "${getString(R.string.camel)}"))
        images.add(Animal(8, R.drawable.coala, "${getString(R.string.coala)}"))
        images.add(Animal(9, R.drawable.fox, "${getString(R.string.fox)}"))
        images.add(Animal(10, R.drawable.lion, "${getString(R.string.lion)}"))
        images.add(Animal(11, R.drawable.monkey, "${getString(R.string.monkey)}"))
        images.add(Animal(12, R.drawable.wolf, "${getString(R.string.wolf)}"))


        buttons = arrayOf(
            imgButton1, imgButton2, imgButton3, imgButton4, imgButton5, imgButton6, imgButton7,
            imgButton8, imgButton9, imgButton10, imgButton11, imgButton12
        )


        images.shuffle()
        createButton()
    }
    fun createButton(){
        for(i in 0..11){

            buttons[i].setBackgroundResource(cardBack)
            buttons[i].setTag("compareCard")
            buttons[i].setOnClickListener {
                if (buttons[i].getTag() == "compareCard" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i].image)
                    buttons[i].setTag(images[i].name)
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                    temp += (clicked - 1)
                    binding.count = temp.toString()
                    viewModel.incCount(temp)

                } else if (buttons[i].getTag() != "compareCard") {
                    buttons[i].setBackgroundResource(cardBack)
                    buttons[i].setTag("compareCard")
                    clicked--
                }

                if (clicked == 2) {
                    turnOver = true
                    if (buttons[i].getTag() == buttons[lastClicked].getTag()) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        turnOver = false
                        clicked = 0
                        var  image  = buttons[i].getTag()
                        textView.setText("${image}")
                    }
                } else if (clicked == 0) {
                    turnOver = false
                }
            }
        }
    }



    fun onButtonReset(view: View) {
        buttons.forEach { button ->
            button.setBackgroundResource(cardBack)
            button.isClickable = true}

            createButton()

            clicked = 0
            turnOver = false
            lastClicked = -1

            temp = 0
            binding.count ="0"
            viewModel.reset()
            textView.setText("")
            images.shuffle()
        }
    }

// Cited from source https://www.youtube.com/watch?v=BGvjScKcW1s&t=610s of author Mr. Kaiser in April 5th ,2022