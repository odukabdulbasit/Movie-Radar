package com.odukabdulbasit.movieradar.recommendfilm

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.odukabdulbasit.movieradar.R
import com.odukabdulbasit.movieradar.databinding.FragmentRecommendFilmBinding

class RecommendFilmFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    val viewModel: RecommendFilmViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, RecommendFilmViewModel.Factory(activity.application)).get(
            RecommendFilmViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentRecommendFilmBinding>(
            inflater, R.layout.fragment_recommend_film, container, false
        )


        binding.lifecycleOwner = this
        binding.recommendfilmmodel = viewModel



        viewModel.isPhoneShaked.observe(viewLifecycleOwner, Observer { isShaked ->
            if (isShaked == true){
                Log.i("Random Movie Value", "${viewModel.randomMovie.value}")
                viewModel.randomMovie.observe(viewLifecycleOwner, Observer {
                    it.let {randomMovie ->
                        findNavController().navigate(RecommendFilmFragmentDirections.actionRecommendFilmFragmentToMovieDetail(it))
                    }
                })

            }
        })

        setUpSensorStuff()
        return binding.root
    }



    private fun setUpSensorStuff() {
        // Create the sensor manager
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Specify the sensor you want to listen to
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // Checks for the sensor we have registered
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            //Log.d("Main", "onSensorChanged: sides ${event.values[0]} front/back ${event.values[1]} ")

            // Sides = Tilting phone left(10) and right(-10)
            val sides = event.values[0]

            // Up/Down = Tilting phone up(10), flat (0), upside-down(-10)
            val upDown = event.values[1]


            /* // Changes the colour of the square if it's completely flat
             val color = if (upDown.toInt() == 0 && sides.toInt() == 0) Color.GREEN else Color.RED

           "up/down ${upDown.toInt()}\nleft/right ${sides.toInt()}"*/

            //burada belirli ideal bir sayi belirlemesi yapmaliyim
            if (upDown.toInt() > 4 && sides.toInt() > 4) {
                Log.i("RecommentFilmFragmnet", "up/down ${upDown.toInt()}\nleft/right ${sides.toInt()}")

                viewModel.isPhoneShaked.observe(this.requireActivity(), Observer {
                    if (it == false)
                        viewModel.setPhoneShaked()
                })
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }

}