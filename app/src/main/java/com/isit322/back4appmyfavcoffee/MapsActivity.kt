package com.isit322.back4appmyfavcoffee

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.isit322.back4appmyfavcoffee.BuildConfig.GOOGLE_MAPS_API_KEY
import com.isit322.back4appmyfavcoffee.databinding.ActivityMapsBinding
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val TAG = MapsActivity::class.java.simpleName
    private val REQUEST_LOCATION_PERMISSION = 1
    var placesClient: PlacesClient? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiKey = "${GOOGLE_MAPS_API_KEY}"
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }
        placesClient = Places.createClient(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as AutocompleteSupportFragment?
        autocompleteFragment!!.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG
            )
        )

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO:refactor - DRY
                ///////
                val latitude = place.latLng.latitude
                val longitude = place.latLng.longitude
                val shopAddress = place.address
                val shopLatLng = LatLng(latitude, longitude)
                val zoomLevel = 12f
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(shopLatLng, zoomLevel))
                map.addMarker(
                    MarkerOptions().position(shopLatLng).title("Address: $shopAddress")
                )
                ///////
            }

            override fun onError(status: Status) {
                Toast.makeText(applicationContext, status.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        // toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val latitude = 47.6062
        val longitude = -122.3321
        val seattleLatLng = LatLng(latitude, longitude)
        val zoomLevel = 12f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(seattleLatLng, zoomLevel))
        map.addMarker(MarkerOptions().position(seattleLatLng).title("Emerald City"))

        // preset markers on map
        // ideally pull location from db when db is complete
        // seattle shop1-shop5
        val shop1 = LatLng(47.6156928534968, -122.32064786604806)
        map.addMarker(MarkerOptions().position(shop1).title("capitol coffee works"))

        val shop2 = LatLng(47.62298866949087, -122.33523547659989)
        map.addMarker(MarkerOptions().position(shop2).title("cascade coffee works"))

        val shop3 = LatLng(47.609796628653754, -122.33627420053199)
        map.addMarker(MarkerOptions().position(shop3).title("pegasus coffee bar"))

        val shop4 = LatLng(47.6223443703369, -122.32574978042835)
        map.addMarker(MarkerOptions().position(shop4).title("analog coffee"))

        val shop5 = LatLng(47.600937978742735, -122.33134633216545)
        map.addMarker(MarkerOptions().position(shop5).title("elm coffee roasters"))

        // san diego shop6-shop10
        val shop6 = LatLng(32.75203981660922, -117.15767389578889)
        map.addMarker(MarkerOptions().position(shop6).title("better buzz coffee"))

        val shop7 = LatLng(32.769073918813696, -117.1219683295316)
        map.addMarker(MarkerOptions().position(shop7).title("dark horse coffee roasters"))

        val shop8 = LatLng(32.73355846223757, -117.1288347845811)
        map.addMarker(MarkerOptions().position(shop8).title("seven seas roasting company"))

        val shop9 = LatLng(32.763765406884495, -117.14231814239241)
        map.addMarker(MarkerOptions().position(shop9).title("communal coffee"))

        val shop10 = LatLng(32.831998504844876, -117.18406459139895)
        map.addMarker(MarkerOptions().position(shop10).title("the forum coffee house"))

        // bellevue shops11-shop15
        val shop11 = LatLng(47.58696894748818, -122.14311717468456)
        map.addMarker(MarkerOptions().position(shop11).title("5 stones coffee co."))

        val shop12 = LatLng(47.58349527874228, -122.14003040816425)
        map.addMarker(MarkerOptions().position(shop12).title("cypress coffee company"))

        val shop13 = LatLng(47.578856597606, -122.169755821256)
        map.addMarker(MarkerOptions().position(shop13).title("mini's coffee & donuts"))

        val shop14 = LatLng(47.610708765945525, -122.17898864841133)
        map.addMarker(MarkerOptions().position(shop14).title("copper kettle coffee bar"))

        val shop15 = LatLng(47.617545478996725, -122.18722706839905)
        map.addMarker(MarkerOptions().position(shop15).title("woods coffee"))

        setMapLongClick(map)
        setPoiClick(map)
        setMapStyle(map)
        enableMyLocation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.map_options, menu)
        return true
    }

    // navigate to a destination when an item's clicked on the toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            // A Snippet is Additional text that's displayed below the title.
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(getString(R.string.dropped_pin))
                    .snippet(snippet)
            )
        }
    }

    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { poi ->
            val poiMarker = map.addMarker(
                MarkerOptions()
                    .position(poi.latLng)
                    .title(poi.name)
            )
            if (poiMarker != null) {
                poiMarker.showInfoWindow()
            }
        }
    }

    private fun setMapStyle(map: GoogleMap) {
        try {
            // Customize the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.map_style
                )
            )
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }
}