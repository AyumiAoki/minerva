package com.example.minerva.ui.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.minerva.R
import com.example.minerva.ui.viewmodel.UsuarioViewModel
import com.example.minerva.util.ConfiguracaoFirebase
import com.example.minerva.util.UsuarioFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream

class UsuarioFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel
    private lateinit var mListener: CreateUsuarioListener
    private lateinit var imagem: ImageView
    private lateinit var imagemEditarFoto: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_usuario, container, false)
        val textViewNome: TextView = root.findViewById(R.id.text_nome_usuario_usuario)

        val buttonSair = root.findViewById<View>(R.id.button_sair)
        val editMudarNome = root.findViewById<EditText>(R.id.edit_mudar_nome)
        val buttonMudarNome = root.findViewById<View>(R.id.button_mudar_nome)
        val buttonSairMudarNome = root.findViewById<View>(R.id.button_sair_mudar_nome)
        val buttonEditarFoto = root.findViewById<View>(R.id.button_editar_foto)
        imagemEditarFoto = root.findViewById<ImageView>(R.id.image_adicionar_foto)

        textViewNome.text = UsuarioFirebase.usuarioAtual?.displayName ?: "Visitante"
        imagem = root.findViewById(R.id.image_usuario)
        if (mListener.passarImagem() != null) {
            imagem.setImageBitmap(mListener.passarImagem())
            imagemEditarFoto.setImageDrawable(requireContext().getDrawable(R.drawable.ic_editar))
        }
        buttonEditarFoto.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, 1)
        }
        buttonSair.setOnClickListener {
            mListener.onListenerUsuario(4)
        }
        editMudarNome.setOnEditorActionListener { _, actionId, event ->
            if (event != null && KeyEvent.KEYCODE_ENTER == event.keyCode || actionId == EditorInfo.IME_ACTION_DONE) {
                val user = FirebaseAuth.getInstance().currentUser

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(editMudarNome.text.toString())
                    .build()

                user!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            println("User profile updated.")
                        }
                    }
                textViewNome.text = editMudarNome.text.toString()
                textViewNome.visibility = View.VISIBLE
                editMudarNome.visibility = View.INVISIBLE
                buttonMudarNome.visibility = View.VISIBLE
                buttonSairMudarNome.visibility = View.INVISIBLE
                mListener.onListenerUsuario(2)
            }
            false
        }

        buttonMudarNome.setOnClickListener {
            textViewNome.visibility = View.INVISIBLE
            editMudarNome.visibility = View.VISIBLE
            buttonMudarNome.visibility = View.INVISIBLE
            buttonSairMudarNome.visibility = View.VISIBLE
            editMudarNome.setText(textViewNome.text)
            editMudarNome.setSelection(editMudarNome.text.length)
            editMudarNome.requestFocus()
            mListener.onListenerUsuario(1)
        }
        buttonSairMudarNome.setOnClickListener {
            mListener.onListenerUsuario(2)
            textViewNome.visibility = View.VISIBLE
            editMudarNome.visibility = View.INVISIBLE
            buttonMudarNome.visibility = View.VISIBLE
            buttonSairMudarNome.visibility = View.INVISIBLE
        }

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {

            activity as CreateUsuarioListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " deve implementar CreateEmailListener")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val image: Bitmap?

        try {
            val localImagem = data!!.data
            image = MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver,
                localImagem
            )
            imagem.setImageBitmap(image)
            mListener.atualizarImagem(image)
            imagemEditarFoto.setImageDrawable(requireContext().getDrawable(R.drawable.ic_editar))
            //Recuperar dados da imagem para o firebase
            val baos = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 70, baos)
            val dadosImagem = baos.toByteArray()

            //Salvar imagem no firebase
            val imagemRef: StorageReference = ConfiguracaoFirebase.firebaseStorage!!.child("imagens")
                .child("perfil").child(UsuarioFirebase.identificadorUsuario)
                .child(FirebaseAuth.getInstance().currentUser!!.uid + ".jpeg")

            val uploadTask: UploadTask = imagemRef.putBytes(dadosImagem)
            uploadTask.addOnSuccessListener {
                Toast.makeText( requireContext(), "Sucesso ao fazer upload da imagem", Toast.LENGTH_SHORT).show()
                imagemRef.downloadUrl.addOnCompleteListener {
                    try {
                        UsuarioFirebase.usuarioAtual
                        UserProfileChangeRequest.Builder()
                            .setPhotoUri(it.result)
                            .build()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Erro ao fazer upload da imagem",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface CreateUsuarioListener {
        fun onListenerUsuario(codigo: Int)
        fun passarImagem(): Bitmap?
        fun atualizarImagem(bitmap: Bitmap)
    }

}