package com.dicoding.fgo_servant_info

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_SERVANT = "extra_servant"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val servant = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Servant>(EXTRA_SERVANT, Servant::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Servant>(EXTRA_SERVANT)
        }

        val servantName: TextView = findViewById(R.id.servant_name)
        val servantDescription: TextView = findViewById(R.id.servant_description)
        val servantNoblePhantasm: TextView = findViewById(R.id.servant_noble_phantasm)
        val servantNpDescription: TextView = findViewById(R.id.servant_np_description)
        val servantPhoto: ImageView = findViewById(R.id.servant_photo)
        val btnShare: Button = findViewById(R.id.action_share)

        if (servant != null) {
            servantName.text = servant.name
            servantDescription.text = servant.description
            servantNoblePhantasm.text = servant.noblePhantasm
            servantNpDescription.text = servant.npDescription
            servantPhoto.setImageResource(servant.photo)
        }

        btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.action_share -> {
                val servant = if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra<Servant>(EXTRA_SERVANT, Servant::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<Servant>(EXTRA_SERVANT)
                }

                if (servant != null) {
                    val shareText = """
                    ${servant.name}
                    ${servant.description}
                    ${servant.noblePhantasm}
                    ${servant.npDescription}
                """.trimIndent()

                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, shareText)
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(shareIntent, "Share via"))
                }
            }
        }
    }
}