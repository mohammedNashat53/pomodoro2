package com.example.pomodoro2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.view.View
import android.view.WindowManager
import com.example.pomodoro2.databinding.ActivityMainBinding

const val TAG = "MainActivity2"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var timer: CountDownTimer

    private var startInMili: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btStart.setOnClickListener {
            binding.minutesLayout.visibility = View.VISIBLE
            it.visibility = View.GONE
            chooseMinutes()
        }

        binding.tvReset.setOnClickListener {
            reset()
        }

        setContentView(binding.root)

    }

    private fun chooseMinutes() {
        binding.tvFifeMin.setOnClickListener {
            startInMili = 5 * 60 * 1000
            countDown(startInMili)
        }
        binding.tvTwentyMin.setOnClickListener {
            startInMili = 20 * 60 * 1000
            countDown(startInMili)
        }
        binding.tvTwityFifeMin.setOnClickListener {
            startInMili = 25 * 60 * 1000
            countDown(startInMili)
        }
        binding.tvThirtyMin.setOnClickListener {
            startInMili = 30 * 60 * 1000
            countDown(startInMili)
        }

    }

    private fun countDown(startInMili: Long) {

        itIsWorking()
        timer = object : CountDownTimer(startInMili, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.apply {
                    tvcounter.text = DateUtils.formatElapsedTime(millisUntilFinished.div(1000))
                    progressBar.progress =
                        millisUntilFinished.toDouble().div(startInMili.toDouble()).times(100)
                            .toInt()
                }
            }

            override fun onFinish() {
                val mediaPlayer = MediaPlayer.create(this@MainActivity,R.raw.aa)
                mediaPlayer.start()
                reset()
            }

        }.start()

    }

    private fun reset() {
        binding.apply {
            tvcounter.text = getString(R.string.start_time)
            tvTitle.text = getString(R.string.title)
            progressBar.progress = 100
            btStart.visibility = View.VISIBLE
            tvReset.visibility = View.GONE
        }
        timer.cancel()
    }

    private fun itIsWorking() {
        binding.apply {
            minutesLayout.visibility = View.GONE
            tvReset.visibility = View.VISIBLE
            tvTitle.text = getString(R.string.keep_going)
        }
    }



}