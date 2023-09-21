package com.example.myapplication

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlins._12KtHttp.ApiServer
import com.example.kotlins._12KtHttp.v3_suspend.KtHttpV3
import com.example.kotlins._12KtHttp.bean.RepoList
import com.example.kotlins._12KtHttp.v3_suspend.Callback
import com.example.kotlins._12KtHttp.v3_suspend.await
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

            }
        })

        testKtHttpV3()
        testKtHttpV32()
    }

    // 支持挂起函数
    private fun testKtHttpV32() = runBlocking {
        KtHttpV3.baseUrl = "https://api.github.com"

        val apiServer = KtHttpV3.create(ApiServer::class.java)

        val deferred = async {
            val ktCall = apiServer.reposSuspend(lang = "Kotlin", since = "weekly")
            ktCall.await()
        }
        println("----result111： $deferred")
        val repoList = deferred.await()

//        println("----result222：" + (repoList == null?"null":repoList.toString))
        println("----result222:${repoList.toString()}")
    }

    private fun testKtHttpV3() {

        KtHttpV3.baseUrl = "https://api.github.com"

        val apiServer = KtHttpV3.create(ApiServer::class.java)

        val ktCall = apiServer.reposSuspend(lang = "Kotlin", since = "weekly")
        println("----testKtHttpV3-111 $ktCall")

        ktCall.request(object : Callback<RepoList> {
            override fun onSuccess(data: RepoList) {
                println("----testKtHttpV3-111 onSuccess: $data")
            }

            override fun onFail(throwable: Throwable) {
                println("----testKtHttpV3-111 onFail: $throwable")
            }
        })

    }
}