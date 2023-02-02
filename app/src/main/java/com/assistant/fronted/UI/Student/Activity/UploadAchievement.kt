package com.assistant.fronted.UI.Student.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_DOCUMENTS
import android.util.Log
import android.view.View
import android.widget.Toast
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout
import com.assistant.fronted.R
import com.assistant.fronted.databinding.ActivityUploadAchievementBinding
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.util.ArrayList

class UploadAchievement : AppCompatActivity(), BGASortableNinePhotoLayout.Delegate {

    lateinit var binding:ActivityUploadAchievementBinding
    private val PRC_PHOTO_PICKER = 1
    private val RC_CHOOSE_PHOTO = 1
    private val RC_PHOTO_PREVIEW = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUploadAchievementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //最大设置九张图
        binding.mPhotosSnpl.maxItemCount=9
        //图片可编辑
        binding.mPhotosSnpl!!.isEditable = true
        //是否显示加号
        binding.mPhotosSnpl!!.isPlusEnable = true
        //是否可拖拽排序
        binding.mPhotosSnpl!!.isSortable = true
        // 设置拖拽排序控件的代理
        binding.mPhotosSnpl!!.setDelegate(this)

    }

    @AfterPermissionGranted(1)
    private fun choicePhotoWrapper() {
        val perms =
            arrayOf<String>(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            val takePhotoDir =
                File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto")
            val photoPickerIntent = BGAPhotoPickerActivity.IntentBuilder(this)
                .cameraFileDir(takePhotoDir ) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                .maxChooseCount(binding.mPhotosSnpl!!.maxItemCount - binding.mPhotosSnpl!!.itemCount) // 图片选择张数的最大值
                .selectedPhotos(null) // 当前已选中的图片路径集合
                .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                .build()
            startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO)
        } else {
            EasyPermissions.requestPermissions(
                this,
                "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照",
                PRC_PHOTO_PICKER,
                *perms
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
    override fun onClickAddNinePhotoItem(
        sortableNinePhotoLayout: BGASortableNinePhotoLayout?,
        view: View?,
        position: Int,
        models: ArrayList<String>?
    ) {
       choicePhotoWrapper()
    }

    override fun onClickDeleteNinePhotoItem(
        sortableNinePhotoLayout: BGASortableNinePhotoLayout?,
        view: View?,
        position: Int,
        model: String?,
        models: ArrayList<String>?
    ) {
        binding.mPhotosSnpl!!.removeItem(position);

    }

    override fun onClickNinePhotoItem(
        sortableNinePhotoLayout: BGASortableNinePhotoLayout?,
        view: View?,
        position: Int,
        model: String?,
        models: ArrayList<String>?
    ) {
        val photoPickerPreviewIntent = BGAPhotoPickerPreviewActivity.IntentBuilder(this)
            .previewPhotos(models) // 当前预览的图片路径集合
            .selectedPhotos(models) // 当前已选中的图片路径集合
            .maxChooseCount(binding.mPhotosSnpl!!.maxItemCount) // 图片选择张数的最大值
            .currentPosition(position) // 当前预览图片的索引
            .isFromTakePhoto(false) // 是否是拍完照后跳转过来
            .build()
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW)
    }

    override fun onNinePhotoItemExchanged(
        sortableNinePhotoLayout: BGASortableNinePhotoLayout?,
        fromPosition: Int,
        toPosition: Int,
        models: ArrayList<String>?
    ) {
        Toast.makeText(this, "排序发生变化", Toast.LENGTH_SHORT).show();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
            if (binding.mSingleChoiceCb!!.isChecked) {
                Log.d("photodata", binding.mPhotosSnpl!!.data.joinToString(","))
                binding.mPhotosSnpl!!.data = BGAPhotoPickerActivity.getSelectedPhotos(data)
            } else {
                binding.mPhotosSnpl!!.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data))
            }
        } else if (requestCode == RC_PHOTO_PREVIEW) {
            binding.mPhotosSnpl!!.data = BGAPhotoPickerPreviewActivity.getSelectedPhotos(data)
        }
    }
}