package com.impax.mgeni.ocr
import android.util.Log
import com.google.mlkit.vision.text.Text
import com.impax.mgeni.ocr.camera.IdData
import ocr.MrzReader


object CardConnector {
    val mrzReader = MrzReader()

    fun onDetailsCaptured(details: MutableList<Text.TextBlock>, mrzResponse: MRZResponse) {
        val mrzString = cleanMRZ(details.last().text)

        val cardType = mrzReader.getCardType(mrzString)

        val isValidCard = mrzReader.isValidCard(cardType, mrzString)

        if (!isValidCard) {

            return
        }else{
            val readCard = mrzReader.readDocument(cardType, mrzString)

            val card = IdData(
                readCard.firstName,
                readCard.secondName,
                readCard.lastName,
                readCard.gender,
                readCard.documentNumber,
                readCard.dateOfBirth,
                readCard.id,
                readCard.country,
            )
            mrzResponse.cardResponse(card)
        }


    }

    //TODO implement clearing
    fun clear(){
       // mrzReader
    }

    private fun cleanMRZ(details_: String): String {
        var details = details_.replace(" ", "")
        details = details.replace("«", "<")
        details = details.replace("e", "<")
        details = details.replace("€", "<")
        return details
    }
}