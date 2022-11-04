package com.ktyoung0507.seoulpubliclibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.ktyoung0507.seoulpubliclibraries.data.Library
import com.ktyoung0507.seoulpubliclibraries.databinding.ActivityMapsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun loadLibraries() {
        val retrofit = Retrofit.Builder().baseUrl(SeoulOpenApi.DOMAIN).addConverterFactory(GsonConverterFactory.create()).build()
        val seoulOpenService = retrofit.create(SeoulOpenService::class.java)

        seoulOpenService.getLibrary(SeoulOpenApi.API_KEY).enqueue(object: Callback<Library> {
            override fun onFailure(call: Call<Library>, t: Throwable) {
                Toast.makeText(baseContext, "서버에서 데이터를 가져올 수 없습니다.", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Library>, response: Response<Library>) {
                showLibraries(response.body() as Library)
            }
        })
    }

    fun showLibraries(libraries: Library) {
        val latLngBounds = LatLngBounds.builder()

        for (lib in libraries.SeoulPublicLibraryInfo.row) {
            val position = LatLng(lib.XCNTS.toDouble(), lib.YDNTS.toDouble())
            val marker = MarkerOptions().position(position).title(lib.LBRRY_NAME)
            mMap.addMarker(marker)

            latLngBounds.include(marker.position)
        }
        val bounds = latLngBounds.build()
        val padding = 0
        val updated = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        mMap.moveCamera(updated)
    }
}