package org.sopt.sample.presentation

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    //getContent나 pick을 써도 된다!
    //pickVisualMedia는 intent에 넘겨지는 정보가 다르다.
    //registerForActivityResult도 StartActivityForResult기반이라 intent로 넘겨주는데, getContent는 intent의 getContent메시지에 담아서 넘겨주고
    //intent의 데이터를 actionopendocument라는 것에 담아서 넘겨준다? 개인적으로 UI가 더 이쁘다.
    //일단 실습으로는 getContent를 해보고,
    //사진첩에서 가져올 때에는 pickVisualMedia나 pickMultipleVisualMedia(여러개) 로 기기의 이미지를 가져올 수 있다!

    //GetContent로 이미지 가져오기1
//    private val getContentLauncher =
//        registerForActivityResult(ActivityResultContracts.GetContent()) {
//            binding.image.load(it)
//        }

    //pickVisualMedia로 이미지 가져오기1
//    private val pickVmLauncher =
//        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
//            binding.image.load(it)
//        }
    //pickMultipleVisualMedia로 이미지 가져오기1
    private val pickMultiVmLauncher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(3)) {
            binding.image1.load(it[0])
            binding.image2.load(it[1])
            binding.image3.load(it[2])
        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //registerForActivityResult로 이미지뿐만 아니라 권한도 가져올 수 있다!
        val permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            }
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

        //GetContent로 이미지 가져오기2
//        binding.textView.setOnClickListener {
//            getContentLauncher.launch("image/*")
//        }

//        //pickVisualMedia로 이미지 가져오기2
//        binding.textView.setOnClickListener {
//            pickVmLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//        }

//        //pickMultipleVisualMedia로 이미지 가져오기2
        binding.textView.setOnClickListener {
            pickMultiVmLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }

}