package Jogo

import javax.swing.*
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.imageio.ImageReader
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants
import javax.swing.text.*
import java.awt.Color
import kotlin.random.Random
// tonho: tamanho da página: grande ou pequena; col: se vai ser colorido ou não; deses: se a imagem vai estar mais definida ou mais embasada (Smoth), gif: as imagens para o tamagochi, status: titulo para o jogo, intensi: para definir a intensidade da art ascii"
class janela(tamanhoDaJanela: Boolean = true, col: Boolean = true, desenho: Boolean = false, caminhoDasImagens: String, status : String, intensidade : Int, intensidadeCustomizada: String = "", velocidade: Long) : JFrame() {
    var mudarEstilo = mutableListOf<estilo>()

    data class estilo(val frames: MutableList<String>, val cores: MutableList<MutableList<Color>>)
val velocidade = velocidade
    val tonho = tamanhoDaJanela;
    var intensicustom = intensidadeCustomizada
    val col = col;
    val deses = desenho;
    var gif = caminhoDasImagens;
    val intensi = intensidade
    val blocoDeTexto: JTextPane = JTextPane()
    var cor = mutableListOf<Color>()
    var framesAscii = mutableListOf<String>()
    var cores = mutableListOf<MutableList<Color>>()
    var quadros = ""
    var intensidade = arrayOf(
        "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:," + "\"^`\'. ",
        "$".repeat(9) + "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:," + "\"^`\'. ",
        "$".repeat(9) + "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:," + "\"^`\'. " + " ".repeat(9),
        "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,",
        "@%#*+=-:. ",
        " .:-=+*#%@",
        " .'`^,:;Il!i",
        "$@B%8&WM#oahkbdpq",
        "$@B%8&WM#oahkbdpqwmZO0QLCJUYX",
        "$@%8&*#/-_+~<>;:,. ",
        " .'`^,:;Il!i><~+_-?][}{1)(|/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao#MW&8%B@$"
    )

    fun tamanho(){
        add(blocoDeTexto)
SwingUtilities.invokeLater {
    var document = DefaultStyledDocument()
    blocoDeTexto.document = document
    blocoDeTexto.text = framesAscii[0]
this.pack()
}
        if(processoDaAnimacao != null && !processoDaAnimacao!!.isAlive){
            execucaoDaAnimacao()
            processoDaAnimacao?.start()
        }


    }

    fun aplicacaoDaIntensidade11(me: Int): String {
        var n = "${intensidade[0][me * (intensidade[0].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade12(me: Int): String {
        var n = "${intensidade[1][me * (intensidade[0].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade13(me: Int): String {
        var n = "${intensidade[2][me * (intensidade[0].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade2(me: Int): String {
        var n = "${intensidade[3][me * (intensidade[3].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade3(me: Int): String {
        var n = "${intensidade[4][me * (intensidade[4].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade4(me: Int): String {
        var n = "${intensidade[5][me * (intensidade[5].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade5(me: Int): String {
        var n = "${intensidade[6][me * (intensidade[6].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade6(me: Int): String {
        var n = "${intensidade[7][me * (intensidade[7].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade7(me: Int): String {
        var n = "${intensidade[8][me * (intensidade[8].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade8(me: Int): String {
        var n = "${intensidade[9][me * (intensidade[9].length - 1) / 255]}"
        return (n)
    }
    fun aplicacaoDaIntensidade9(me: Int): String {
        var n = "${intensidade[10][me * (intensidade[10].length - 1) / 255]}"
        return (n)
    }

    fun aplicacaoDaIntensidadeCustomizada(me: Int): String {
        var n = "${intensicustom[me * (intensicustom.length - 1) / 255]}"
        return (n)
    }

    var iniciar: JLabel? = JLabel()

    fun iniciando() {
        iniciar!!.text = "Escreva o nome do TamaGochi"
        add(iniciar)

    }
    var init = true

    fun terminando() {
        if (init){
            init = false
        this.remove(iniciar)
            execucaoDaAnimacao()

        }else{
        if(processoDaAnimacao != null && processoDaAnimacao!!.isAlive){
            mudar = false
            processoDaAnimacao!!.join(500)

        }}
        blocoDeTexto.isVisible = true
        tamanho()
    }

    init {
        if(!col){

        }
        blocoDeTexto.isVisible = false
        isVisible = true
        title = status
        blocoDeTexto.isEditable = false
        blocoDeTexto.background = if (col) {
            Color.BLACK
        } else {
            Color.WHITE
        }
        background = blocoDeTexto.background
        defaultCloseOperation = DO_NOTHING_ON_CLOSE
        blocoDeTexto.font = if (tamanhoDaJanela) Font("Monospaced", Font.PLAIN, 10) else Font("Monospaced", Font.PLAIN, 8)
        layout = GridBagLayout()
        blocoDeTexto.minimumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        size = if (tamanhoDaJanela) {
            Dimension(530, 440)
        } else {
            Dimension(340, 370)
        }
        iniciando()
        processamentoDasImagens()


    }
    var ImagemUnica: File? = null
    fun fechar(){
        defaultCloseOperation = DISPOSE_ON_CLOSE
        isVisible = false
    }
    fun mudarestiloexterno(){
        mudar = false
        processoDaAnimacao?.join(10)
        var numeroaletaroio = Random.nextInt(0, mudarEstilo.size)
        framesAscii = mudarEstilo[numeroaletaroio].frames
        cores = mudarEstilo[numeroaletaroio].cores
        mudar = true
        tamanho()
    }

    fun mudarestilo() {

        var numeroaletaroio = Random.nextInt(0, mudarEstilo.size)
            framesAscii = mudarEstilo[numeroaletaroio].frames
            cores = mudarEstilo[numeroaletaroio].cores
    }

    fun processamentoDasImagens(t: Boolean = tonho, d: Boolean = deses, g: String = gif, i: Int = intensi) {
        mudarEstilo = mutableListOf()
        cores = mutableListOf()
        framesAscii = mutableListOf()
        var caminhoPastasDeImagem = File(g)
       var listimage = caminhoPastasDeImagem.listFiles().filter {it.isDirectory}

        isResizable = false
        listimage.forEach { pastas ->
            var listadeimagem = pastas.listFiles()
            var iniciar = true
            listadeimagem.forEach { image ->
                if (image.extension == "gif") {
                    var inp = ImageIO.createImageInputStream(image)
                    var read: ImageReader = ImageIO.getImageReadersByFormatName("gif").next()

                    read.setInput(inp)
                    val frameCount = read.getNumImages(true)
                    for (frame in 0 ..< frameCount) {
                        val imagem: BufferedImage = read.read(frame)
                        if(iniciar) {
                            ImagemUnica = File(image.path)
                            iniciar = false
                        }
                        var a = if (t == false) 25/* janela pequena*/ else 40 // Janela Grande
                        //l = 70 // janela pequena
                        var l = if (t == false ) 70 /* janela pequena*/ else 80// Janela Grande
                        var realImagem = BufferedImage(l, a, BufferedImage.TYPE_INT_ARGB)
                        if (deses) {
                            val origem = imagem.getScaledInstance(
                                l,
                                a,
                                Image.SCALE_SMOOTH
                            ) //para imagens que tem muitos detalhes, como desenhos
                            var graphicReal: Graphics2D = realImagem.createGraphics()
                            graphicReal.drawImage(origem, 0, 0, l, a, null)
                            graphicReal.dispose()

                        } else {
                            var graphicReal: Graphics2D = realImagem.createGraphics()
                            graphicReal.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                            graphicReal.setRenderingHint(
                                RenderingHints.KEY_RENDERING,
                                RenderingHints.VALUE_RENDER_QUALITY
                            )
                            graphicReal.drawImage(imagem, 0, 0, l, a, null)
                            graphicReal.dispose()
                        }

                        cor = mutableListOf()
                        for ( y in 0 ..< realImagem.height) {
                            var x = 0

                            for (x in 0 ..< realImagem.width) {
                                var c = Color(realImagem.getRGB(x, y))
                                var r = c.red
                                var v = c.green
                                var a = c.blue
                                var media = (r + v + a) / 3
                                for (x in image.name) {
                                    var alt = ""
                                    alt += x

                                }

                                cor.add(c)
                                var intensidade = if (i == 0) {
                                    aplicacaoDaIntensidade11(media)
                                } else if (i == 1) {
                                    aplicacaoDaIntensidade12(media)
                                } else if (i == 2) {
                                    aplicacaoDaIntensidade13(media)
                                } else if (i == 3) {
                                    aplicacaoDaIntensidade2(media)
                                } else if (i == 4) {
                                    aplicacaoDaIntensidade3(media)
                                } else if (i == 5) {
                                    aplicacaoDaIntensidade4(media)
                                } else if (i == 6) {
                                    aplicacaoDaIntensidade5(media)
                                } else if (i == 7) {
                                    aplicacaoDaIntensidade6(media)
                                } else if (i == 8) {
                                    aplicacaoDaIntensidade7(media)
                                } else if (i == 9) {
                                    aplicacaoDaIntensidade8(media)
                                } else if(i == 10) {
                                    aplicacaoDaIntensidade9(media)
                                } else if(i == 11){
                                    aplicacaoDaIntensidadeCustomizada(media)
                                }else{
                                    println("Número custom inválido")
                                    aplicacaoDaIntensidade11(media)
                                }
                                quadros += intensidade
                            }
                            cor.add(Color(0, 0, 0))

                            quadros += "\n"
                            x++
                        }
                        cores.add(cor)
                        framesAscii.add(quadros)
                        quadros = ""
                    }


                } else if (image.extension == "jpg" || image.extension == "png" || image.extension == "jpeg") {
                    var imagem: BufferedImage = ImageIO.read(image)
                    if(iniciar) {
                        ImagemUnica = File(image.path)
                        iniciar = false

                    }
                    var a = if (t == false) 20/* janela pequena*/ else 40 // Janela Grande
                    //l = 70 // janela pequena
                    var l = if (t == false) 40 /* janela pequena*/ else 80 // Janela Grande
                    var realImagem = BufferedImage(l, a, BufferedImage.TYPE_INT_ARGB)
                    if (d == false) {
                        val origem = imagem.getScaledInstance(
                            l,
                            a,
                            Image.SCALE_SMOOTH
                        ) //para imagens que tem muitos detalhes, como desenhos
                        var graphicReal: Graphics2D = realImagem.createGraphics()
                        graphicReal.drawImage(origem, 0, 0, l, a, null)
                        graphicReal.dispose()

                    } else {
                        var graphicReal: Graphics2D = realImagem.createGraphics()
                        graphicReal.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                        graphicReal.setRenderingHint(
                            RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY
                        )
                        graphicReal.drawImage(imagem, 0, 0, l, a, null)
                        graphicReal.dispose()
                    }
                    var x = 0
                    cor = mutableListOf()
                    for(y in 0 ..< realImagem.height) {
                        for(x in 0 ..< realImagem.width) {
                            var c = Color(realImagem.getRGB(x, y))
                            var r = c.red
                            var v = c.green
                            var a = c.blue
                            var me = (r + v + a) / 3


                            cor.add(c)
                            var intensida = if (i == 0) {
                                aplicacaoDaIntensidade11(me)
                            } else if (i == 1) {
                                aplicacaoDaIntensidade12(me)
                            } else if (i == 2) {
                                aplicacaoDaIntensidade13(me)
                            } else if (i == 3) {
                                aplicacaoDaIntensidade2(me)
                            } else if (i == 4) {
                                aplicacaoDaIntensidade3(me)
                            } else if (i == 5) {
                                aplicacaoDaIntensidade4(me)
                            } else if (i == 6) {
                                aplicacaoDaIntensidade5(me)
                            } else if (i == 7) {
                                aplicacaoDaIntensidade6(me)
                            } else if (i == 8) {
                                aplicacaoDaIntensidade7(me)
                            } else if (i == 9) {
                                aplicacaoDaIntensidade8(me)
                            } else if(i == 10) {
                                aplicacaoDaIntensidade9(me)
                            } else if(i == 11){
                                aplicacaoDaIntensidadeCustomizada(me)
                            }else{
                                println("Número custom inválido")
                                aplicacaoDaIntensidade11(me)
                            }
                            quadros += intensida
                        }
                        cor.add(Color(0, 0, 0))

                        quadros += "\n"


                    }
                    cores.add(cor)
                    framesAscii.add(quadros)
                    quadros = ""

                }


            }
            var estilo = estilo(framesAscii, cores)
            mudarEstilo.add(estilo)
            framesAscii = mutableListOf()
            cores = mutableListOf()


        }
        mudarestilo()


    }

    var processoDaAnimacao: Thread? = null
    var mudar = true
    /*fun copy(original: DefaultStyledDocument): DefaultStyledDocument {
        val newDoc = DefaultStyledDocument()

        // Copia o texto e os atributos
        val root = original.rootElements[0] // Elemento raiz
        var offset = 0
        while (offset < original.length) {
            val element = original.getCharacterElement(offset)
            // Copia os atributos para garantir independência
            val attrs = SimpleAttributeSet(element.attributes)
            val endOffset = element.endOffset
            val text = original.getText(offset, endOffset - offset)
            newDoc.insertString(offset, text, attrs)
            offset = endOffset
        }

        return newDoc
    }*/


    /*inner class trabalhosujo : SwingWorker<Document, Document>() { // modo pesado
        override fun doInBackground(): Document {
            var inicio = frameascii[0].length
            while (true) {
                txt.document = frames


                frameascii.forEachIndexed { i, F ->

                    F.forEachIndexed { index, t ->

                        val atributos = SimpleAttributeSet().apply {
                            StyleConstants.setForeground(
                                this,
                                cores[i][index]
                            )

                        }
                        frames.insertString(frames.length, t.toString(), atributos)
                    }
                    if (inicio * frameascii.size < frames.length) {
                        txt.document.remove(0, inicio)
                        Thread.sleep(50)
                        repaint()
                    }
                }

            }

            return (frames)

        }


    }*/
    var listframes = mutableListOf<Document>()
    fun quadroAsciiColorido(image: String,local:Int): Document{
        var doc = DefaultStyledDocument()
        var copiaCores = cores.toList()
        image.forEachIndexed { index, t ->
            val atributos = SimpleAttributeSet().apply {
                StyleConstants.setForeground(
                    this,
                    copiaCores[local][index]

                )

            }

            doc.insertString(doc.length, t.toString(), atributos)
        }
        return doc

    }
    fun quadroAscii(imageAscii: String): Document{
        var doc = DefaultStyledDocument()
       doc.insertString(0,imageAscii,null)
        return doc

    }


    fun criascii(quadros: MutableList<String>) {
        var imagensascii = quadros.toList()

        imagensascii.forEachIndexed{
                i,f  ->
            if(!col){
            listframes.add(quadroAscii(f))}else{
                listframes.add(quadroAsciiColorido(f,i))
            }
        }


    }


    fun continuarascii(): StyledDocument {
        var docu= DefaultStyledDocument()
        var coress = cores.toList()
        var frame = framesAscii.toList()

        frame.forEachIndexed { i, Fram: String ->
            Fram.forEachIndexed { index, t ->

                val atributos = SimpleAttributeSet().apply {
                    StyleConstants.setForeground(
                        this,
                        coress[i][index]
                    )

                }


                    docu.insertString(docu.length, t.toString(), atributos)
            }


            }
        return(docu)

    }
    fun continuarasciisemcor(): DefaultStyledDocument {
        var docu= DefaultStyledDocument()
        var frame = framesAscii

        frame.forEachIndexed { i, Fram: String ->

                docu.insertString(docu.length, Fram, null)

        }
        return(docu)

    }
    fun execucaoDaAnimacao() {
        listframes = mutableListOf()
        criascii(framesAscii)
        blocoDeTexto.isVisible = true
        mudar = true
        processoDaAnimacao = Thread {
        var apontadorDoFrame = 0
            while (mudar) {
                if (apontadorDoFrame == listframes.size - 1) {
                    apontadorDoFrame = 0
                } else {
                    apontadorDoFrame++
                }
if(apontadorDoFrame in listframes.indices){
                    SwingUtilities.invokeLater{
                        blocoDeTexto.document = listframes[apontadorDoFrame]}
                Thread.sleep(velocidade)

                }




        }
                apontadorDoFrame = 0
    }
}

    fun mdg(gq: String = gif) {

        if (processoDaAnimacao != null && processoDaAnimacao!!.isAlive) {
            mudar = false
            processoDaAnimacao!!.join(500)
        }
        processamentoDasImagens(g = gq)
        terminando()

    }
    fun icon(image : File? = ImagemUnica, largura: Int = 20, altura:Int =5): String {
        var i = intensi

        var image = ImageIO.read(image)
        var realImagem = BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB)
        var graphicReal: Graphics2D = realImagem.createGraphics()
        graphicReal.drawImage(image, 0, 0, largura, altura, null)
        graphicReal.dispose()
        var quadroDoIcone = ""
        var x = 0

        for(y in 0 ..< realImagem.height) {

            for (x in 0..<realImagem.width) {
                var c = Color(realImagem.getRGB(x, y))
                var r = c.red
                var v = c.green
                var a = c.blue
                var media = (r + v + a) / 3

                var intensidade = if (i == 0) {
                    aplicacaoDaIntensidade11(media)
                } else if (i == 1) {
                    aplicacaoDaIntensidade12(media)
                } else if (i == 2) {
                    aplicacaoDaIntensidade13(media)
                } else if (i == 3) {
                    aplicacaoDaIntensidade2(media)
                } else if (i == 4) {
                    aplicacaoDaIntensidade3(media)
                } else if (i == 5) {
                    aplicacaoDaIntensidade4(media)
                } else if (i == 6) {
                    aplicacaoDaIntensidade5(media)
                } else if (i == 7) {
                    aplicacaoDaIntensidade6(media)
                } else if (i == 8) {
                    aplicacaoDaIntensidade7(media)
                } else if (i == 9) {
                    aplicacaoDaIntensidade8(media)
                } else {
                    aplicacaoDaIntensidade9(media)
                }
                quadroDoIcone += if (!col) intensidade else "\u001B[38;2;$r;$v;${a}m$intensidade"
            }

            quadroDoIcone += "\n"
        }

        return (quadroDoIcone)
    }

}