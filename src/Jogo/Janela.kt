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
class janela(tonho: Boolean = true, col: Boolean = true, deses: Boolean = false,gif: String, status : String,intensi : Int, customintens: String = "",velocidade: Long) : JFrame() {
    var mudarestilo = mutableListOf<estilo>()

    data class estilo(val frames: MutableList<String>, val cores: MutableList<MutableList<Color>>)
val velocidade = velocidade
    val tonho = tonho;
    var intensicustom = customintens
    val col = col;
    val deses = deses;
    var gif = gif;
    val intensi = intensi
    val txt: JTextPane = JTextPane()
    var cor = mutableListOf<Color>()
    var frameascii = mutableListOf<String>()
    var cores = mutableListOf<MutableList<Color>>()
    var vad = ""
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
        add(txt)
        SwingUtilities.invokeAndWait{
            txt.text = frameascii[0]
            pack()
            size = Dimension(size.width -20,size.height -15)


        }

        mdtc(frameascii)
        if(!processo!!.isAlive){
            processo?.start()
        }



    }

    // Essa parte é das intensidades, em algumas imagens principalmente as que tem fundo fica muito díficil de ver qual é a imagem
    fun inter(me: Int): String {
        var n = "${intensidade[0][me * (intensidade[0].length - 1) / 255]}"
        return (n)
    };
    fun inter1(me: Int): String {
        var n = "${intensidade[1][me * (intensidade[0].length - 1) / 255]}"
        return (n)
    };
    fun inter12(me: Int): String {
        var n = "${intensidade[2][me * (intensidade[0].length - 1) / 255]}"
        return (n)
    };
    fun inter2(me: Int): String {
        var n = "${intensidade[3][me * (intensidade[3].length - 1) / 255]}"
        return (n)
    };
    fun inter3(me: Int): String {
        var n = "${intensidade[4][me * (intensidade[4].length - 1) / 255]}"
        return (n)
    };
    fun inter4(me: Int): String {
        var n = "${intensidade[5][me * (intensidade[5].length - 1) / 255]}"
        return (n)
    };
    fun inter5(me: Int): String {
        var n = "${intensidade[6][me * (intensidade[6].length - 1) / 255]}"
        return (n)
    };
    fun inter6(me: Int): String {
        var n = "${intensidade[7][me * (intensidade[7].length - 1) / 255]}"
        return (n)
    };
    fun inter7(me: Int): String {
        var n = "${intensidade[8][me * (intensidade[8].length - 1) / 255]}"
        return (n)
    };
    fun inter8(me: Int): String {
        var n = "${intensidade[9][me * (intensidade[9].length - 1) / 255]}"
        return (n)
    };
    fun inter9(me: Int): String {
        var n = "${intensidade[10][me * (intensidade[10].length - 1) / 255]}"
        return (n)
    }

    fun intercustom(me: Int): String {
        var n = "${intensicustom[me * (intensicustom.length - 1) / 255]}"
        return (n)
    }

    var iniciar: JLabel? = JLabel()

    fun iniciando() {
        iniciar!!.text = "Escreva o nome do TamaGochi"
        add(iniciar)

    }
    var init = true

    fun terminar() {
        if (init){
            init = false
        this.remove(iniciar)}
        txt.isVisible = true
        tamanho()
    }

    init {
        if(!col){

        }
        txt.isVisible = false
        isVisible = true
        title = status
        txt.isEditable = false
        txt.background = if (col) {
            Color.BLACK
        } else {
            Color.WHITE
        }
        background = txt.background
        defaultCloseOperation = DO_NOTHING_ON_CLOSE
        txt.font = if (tonho) Font("Monospaced", Font.PLAIN, 10) else Font("Monospaced", Font.PLAIN, 8)
        layout = GridBagLayout()
        txt.minimumSize = Dimension(8000, 8000)
        size = if (tonho) {
            Dimension(530, 640)
        } else {
            Dimension(340, 370)
        }
        iniciando()
        giff()


    }
    var Imagemunica: File? = null
    fun fechar(){
        defaultCloseOperation = DISPOSE_ON_CLOSE
        isVisible = false
    }
    fun mudarestiloexterno(){
        mudar = false
        processo?.join(10)
        var numeroaletaroio = Random.nextInt(0, mudarestilo.size)
        frameascii = mudarestilo[numeroaletaroio].frames
        cores = mudarestilo[numeroaletaroio].cores
        mudar = true
        tamanho()
    }

    fun mudarestilo() {

        var numeroaletaroio = Random.nextInt(0, mudarestilo.size)
            frameascii = mudarestilo[numeroaletaroio].frames
            cores = mudarestilo[numeroaletaroio].cores
    }

    fun giff(t: Boolean = tonho, c: Boolean = col, d: Boolean = deses, g: String = gif, i: Int = intensi) {
        mudarestilo = mutableListOf()
        cores = mutableListOf()
        frameascii = mutableListOf()
        var image = File(g)
        var listimage = image.listFiles().filter { it.isDirectory }
        isResizable = false
        listimage.forEach { pastas ->
            var listadeimagem = pastas.listFiles()
            listadeimagem.forEach { image ->
var iniciar = true
                if (image.extension == "gif") {
                    var inp = ImageIO.createImageInputStream(image)
                    var read: ImageReader = ImageIO.getImageReadersByFormatName("gif").next()

                    read.setInput(inp)
                    val frameCount = read.getNumImages(true)
                    for (x in 0 until frameCount) {
                        val frame: BufferedImage = read.read(x)
                        if(iniciar) {
                            Imagemunica = File(image.path)
                            iniciar = false
                        }
                        var a = if (t == false) 25/* janela pequena*/ else 40 // Janela Grande
                        //l = 70 // janela pequena
                        var l = if (t == false ) 70 /* janela pequena*/ else 80// Janela Grande
                        var reals = BufferedImage(l, a, BufferedImage.TYPE_INT_RGB)
                        if (deses) {
                            val origin = frame.getScaledInstance(
                                l,
                                a,
                                Image.SCALE_SMOOTH
                            ) //para imagens que tem muitos detalhes, como desenhos
                            var greals: Graphics2D = reals.createGraphics()
                            greals.drawImage(origin, 0, 0, l, a, null)
                            greals.dispose()

                        } else {

 // para algo mais definido, como pixel art
                            var greals: Graphics2D = reals.createGraphics()
                            greals.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                            greals.setRenderingHint(
                                RenderingHints.KEY_RENDERING,
                                RenderingHints.VALUE_RENDER_QUALITY
                            )
                            greals.drawImage(frame, 0, 0, l, a, null)
                            greals.dispose()
                        }

                        var x = 0
                        cor = mutableListOf()
                        while (x < reals.height) {
                            var j = 0

                            while (j < reals.width) {
                                var c = Color(reals.getRGB(j, x))
                                var r = c.red
                                var v = c.green
                                var a = c.blue
                                var me = (r + v + a) / 3
                                j++
                                for (x in image.name) {
                                    var alt = ""
                                    alt += x

                                }

                                cor.add(c)
                                var intensida = if (i == 0) {
                                    inter(me)
                                } else if (i == 1) {
                                    inter1(me)
                                } else if (i == 2) {
                                    inter12(me)
                                } else if (i == 3) {
                                    inter2(me)
                                } else if (i == 4) {
                                    inter3(me)
                                } else if (i == 5) {
                                    inter4(me)
                                } else if (i == 6) {
                                    inter5(me)
                                } else if (i == 7) {
                                    inter6(me)
                                } else if (i == 8) {
                                    inter7(me)
                                } else if (i == 9) {
                                    inter8(me)
                                } else if(i == 10) {
                                    inter9(me)
                                } else if(i == 11){
                                    intercustom(me)
                                }else{
                                    println("Número custom inválido")
                                    inter(me)
                                }
                                vad += intensida
                            }
                            cor.add(Color(0, 0, 0))

                            vad += "\n"
                            x++
                        }
                        cores.add(cor)
                        frameascii.add(vad)
                        vad = ""
                    }


                } else if (image.extension == "jpg" || image.extension == "png" || image.extension == "jpeg") {
                    var frame: BufferedImage = ImageIO.read(image)
                    if(iniciar) {
                        Imagemunica = File(image.path)
                        iniciar = false

                    }
                    var a = if (t == false) 20/* janela pequena*/ else 40 // Janela Grande
                    //l = 70 // janela pequena
                    var l = if (t == false) 40 /* janela pequena*/ else 80 // Janela Grande
                    var reals = BufferedImage(l, a, BufferedImage.TYPE_INT_RGB)
                    if (d == false) {
                        val origin = frame.getScaledInstance(
                            l,
                            a,
                            Image.SCALE_SMOOTH
                        ) //para imagens que tem muitos detalhes, como desenhos
                        var greals: Graphics2D = reals.createGraphics()
                        greals.drawImage(origin, 0, 0, l, a, null)
                        greals.dispose()

                    } else {

                        reals =
                            BufferedImage(l, a, BufferedImage.TYPE_INT_RGB) // para algo mais definido, como pixel art
                        var greals: Graphics2D = reals.createGraphics()
                        greals.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                        greals.setRenderingHint(
                            RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY
                        )
                        greals.drawImage(frame, 0, 0, l, a, null)
                        greals.dispose()
                    }
                    var x = 0
                    cor = mutableListOf()
                    while (x < reals.height) {
                        var j = 0

                        while (j < reals.width) {
                            var c = Color(reals.getRGB(j, x))
                            var r = c.red
                            var v = c.green
                            var a = c.blue
                            var me = (r + v + a) / 3
                            j++


                            cor.add(c)
                            var intensida = if (i == 0) {
                                inter(me)
                            } else if (i == 1) {
                                inter1(me)
                            } else if (i == 2) {
                                inter12(me)
                            } else if (i == 3) {
                                inter2(me)
                            } else if (i == 4) {
                                inter3(me)
                            } else if (i == 5) {
                                inter4(me)
                            } else if (i == 6) {
                                inter5(me)
                            } else if (i == 7) {
                                inter6(me)
                            } else if (i == 8) {
                                inter7(me)
                            } else if (i == 9) {
                                inter8(me)
                            } else if(i == 10) {
                                inter9(me)
                            } else if(i == 11){
                                intercustom(me)
                            }else{
                                println("Número custom inválido")
                                inter(me)
                            }
                            vad += intensida
                        }
                        cor.add(Color(0, 0, 0))

                        vad += "\n"
                        x++


                    }
                    cores.add(cor)
                    frameascii.add(vad)
                    vad = ""

                }


            }
            var estiloa = estilo(frameascii, cores)
            mudarestilo.add(estiloa)
            frameascii = mutableListOf()
            cores = mutableListOf()


        }
        mudarestilo()


    }

    var processo: Thread? = null
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

    fun criascii(frams: MutableList<String>): Document {

        var doc = DefaultStyledDocument()

        var con = 0

        for (T in frams) {


            T.forEachIndexed { index, t ->
                val atributos = SimpleAttributeSet().apply {
                    StyleConstants.setForeground(
                        this,
                        cores[con][index]

                    )

                }

                doc.insertString(doc.length, t.toString(), atributos)
            }
            con++
        }
        return (doc)
    }

    fun continuarascii():StyledDocument{
        var docu= DefaultStyledDocument()
        var coress = cores.toList()
        var frame = frameascii.toList()

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
    fun continuarasciisemcor():StyledDocument{
        var docu= DefaultStyledDocument()
        var frame = frameascii

        frame.forEachIndexed { i, Fram: String ->

                docu.insertString(docu.length, Fram, null)

        }
        return(docu)

    }
    var frames : Document = DefaultStyledDocument()
    fun mdtc(F: MutableList<String>) {
           var doc = continuarascii()
        txt.isVisible = true


        txt.isVisible = true
            var inicio = F[0].length
            mudar = true
        processo = Thread {
            while (mudar) {


                if(txt.text.length > frameascii[0].length && mudar){
                    Thread.sleep(velocidade)


                    SwingUtilities.invokeAndWait{
                        txt.styledDocument.remove(0,frameascii[0].length)

                    }
        }

                if(F[0].length *2 > txt.document.length){
                    SwingUtilities.invokeAndWait() {
                        txt.styledDocument = doc
                    }
                }

                if(inicio*frameascii.size - inicio == txt.document.length){
                    if(col){
                        SwingUtilities.invokeAndWait() {
                            doc = continuarascii()
                        }}else{
                            SwingUtilities.invokeAndWait(){
                                doc = continuarasciisemcor()
                            }
                    }

                }



        }


















    }

}
    fun mtt(s: String) {
        title = s
    }

    fun espera() {
        Thread.sleep(10)
        mudar = false
        processo?.join()
    }

    fun mdg(gq: String = gif) {

mudar = false
        processo!!.join()
mudar = true
        giff(g = gq)
        terminar()

    }
    fun icon(image : File? = Imagemunica, largura: Int = 20, altura:Int =5): String {
        var i = intensi

        var image = ImageIO.read(image)
        var reals = BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB)
        var greals: Graphics2D = reals.createGraphics()
        greals.drawImage(image, 0, 0, largura, altura, null)
        greals.dispose()
        var vadi = ""
        var x = 0

        while (x < reals.height) {
            var j = 0

            while (j < reals.width) {
                var c = Color(reals.getRGB(j, x))
                var r = c.red
                var v = c.green
                var a = c.blue
                var me = (r + v + a) / 3
                j++

                var intensida = if (i == 0) {
                    inter(me)
                } else if (i == 1) {
                    inter1(me)
                } else if (i == 2) {
                    inter12(me)
                } else if (i == 3) {
                    inter2(me)
                } else if (i == 4) {
                    inter3(me)
                } else if (i == 5) {
                    inter4(me)
                } else if (i == 6) {
                    inter5(me)
                } else if (i == 7) {
                    inter6(me)
                } else if (i == 8) {
                    inter7(me)
                } else if (i == 9) {
                    inter8(me)
                } else {
                    inter9(me)
                }
                vadi += if (col == false) intensida else "\u001B[38;2;$r;$v;${a}m$intensida"
            }

            vadi += if (col == false) "\n" else "\u001B[38;2;0;0;0m\n\u001B[0m"
            x++
        }

        return (vadi)
    }

}