import Jogo.janela
import java.io.File
import javax.sound.sampled.*
import kotlin.random.Random
var diarecord = 0
var tocador = Player()
var pastamusicaJogo = File("./Assets/Músicas/Jogo/").listFiles().filter { it.extension == "wav" }.sortedBy { it.name.filter { it.isDigit() }.toInt() }
var pastamusicaGabriel = File("./Assets/Músicas/Gabriel/").listFiles().filter { it.extension == "wav" }
var pastamusicaJailson = File("./Assets/Músicas/Jailson/").listFiles().filter { it.extension == "wav" }
var musicasGabriel = mutableListOf<String>()
var musicasJailson = mutableListOf<String>()
var musicasJogo = mutableListOf<String>()
var  interaçãodeutudoerrado = arrayOf("A e B não querem fazer nada","A e B não querem se falar agora","A e B querem ficar longe um do outro")
var interaçãonegativairmãoirmã = arrayOf(
    "B limpou o quarto que divide com A sem reclamar, mas A bagunçou de novo.",
    "B arrumou o controle remoto que A deixou bagunçado, mas A desarrumou outra vez.",
    "B gravou um programa que A queria assistir, mas A deletou sem ver.",
    "B levou o lixo para fora antes que A precisasse pedir, mas A nem notou.",)
var interaçãopositivairmãoirmã = arrayOf(
    "B limpou o quarto que divide com A sem reclamar.",
    "B arrumou o controle remoto que A deixou bagunçado.",
    "B gravou um programa que A queria assistir.",
    "B levou o lixo para fora antes que A precisasse pedir.")
var interaçãonegativafilhosfilhos = arrayOf("A quis passar a tarde desenhando com B, mas B não se interessou.",
    "A tentou ler uma história bem legal para B antes de dormir, mas B não quis ouvir.",
    "A se ofereceu para ajudar B a montar aquele quebra-cabeça enorme, mas B recusou.",
    "A quis levar B para um passeio no parque hoje, mas B preferiu ficar em casa.",
    "A convidou B para jogar videogame juntos, mas B não quis e eles não se divertiram.",
    "A tentou ensinar B a fazer um bolo delicioso na cozinha, mas B não quis aprender.",
    "A chamou B para assistir a um filme juntos, mas B não quis e eles não riram.",
    "A sugeriu ir com B comprar um presente especial para o amigo, mas B não quis ir.",
    "A quis brincar de esconde-esconde com B no quintal, mas B não aceitou.")
var interaçãonegativapaisfilhos = arrayOf("A não quis passar a tarde desenhando com B, embora B quisesse.",
    "A não leu uma história bem legal para B antes de dormir, apesar de B pedir.",
    "A não ajudou B a montar aquele quebra-cabeça enorme, mesmo B querendo ajuda.",
    "A não levou B para um passeio no parque hoje, apesar do desejo de B.",
    "A não jogou videogame com B, e eles não se divertiram, embora B quisesse.",
    "A não ensinou B a fazer um bolo delicioso na cozinha, mesmo com B interessado.",
    "A não assistiu a um filme com B, e eles não riram, apesar de B sugerir.",
    "A não foi com B comprar um presente especial para o amigo, embora B quisesse ir.",
    "A não brincou de esconde-esconde com B no quintal, apesar de B insistir.")
var interaçãopositivafilhos = arrayOf("A passou a tarde desenhando com B.",
    "A leu uma história bem legal para B antes de dormir.",
    "A ajudou B a montar aquele quebra-cabeça enorme.",
    "A levou B para um passeio no parque hoje.",
    "A e B jogaram videogame juntos e se divertiram muito.",
    "A ensinou B a fazer um bolo delicioso na cozinha.",
    "A assistiu a um filme com B e eles riram bastante.",
    "A foi com B comprar um presente especial para o amigo.",
    "A brincou de esconde-esconde com B no quintal.")
var interaçãopositivapais = arrayOf("B e A assistiram ao nascer do sol no fim de semana.","B discutiu com A sobre o melhor time de futebol.",
    "B jogou bola com A no quintal por horas.",
    "B e A construíram uma casa de brinquedo juntos.",
    "B assistiu ao jogo favorito de A na TV com ele.",
    "B ajudou A a consertar o carro na garagem.",
    "B foi com A pescar no lago no fim de semana.",
    "B correu com A no parque para treinar.")
var interaçãonegativafilhospais = arrayOf("B não quis assistir ao nascer do sol no fim de semana com A, embora A quisesse.",
    "B se recusou a discutir com A sobre o melhor time de futebol, mesmo A insistindo.",
    "B não jogou bola com A no quintal por horas, apesar de A querer.",
    "B não ajudou A a construir uma casa de brinquedo juntos, embora A tivesse pedido.",
    "B não assistiu ao jogo favorito de A na TV com ele, mesmo A convidando.",
    "B não foi com A pescar no lago no fim de semana, embora A quisesse companhia.",
    "B não correu com A no parque para treinar, apesar de A sugerir.")
var interaçãonegativapaispais = arrayOf("B quis assistir ao nascer do sol no fim de semana com A, mas A não se interessou.",
    "B tentou discutir com A sobre o melhor time de futebol, mas A não quis conversar.",
    "B chamou A para jogar bola no quintal por horas, mas A recusou.",
    "B sugeriu que ele e A construíssem uma casa de brinquedo juntos, mas A não quis.",
    "B convidou A para assistir ao seu jogo favorito na TV, mas A não aceitou.",
    "B pediu ajuda a A para consertar o carro na garagem, mas A não quis ajudar.",
    "B insistiu para ir com A pescar no lago no fim de semana, mas A recusou.",
    "B propôs que ele e A fizessem um churrasco bem animado, mas A não topou.",
    "B perguntou a A como resolver um dever difícil, mas A não quis explicar.",
    "B chamou A para correr no parque para treinar, mas A não quis ir.")
var interaçãopositivaparceiraparceiro = arrayOf(
    "B deixou um bilhete carinhoso para A encontrar.",
    "B escolheu uma playlist romântica para tocar à noite com A.",
    "B comprou um presente surpresa para A.",
    "B planejou uma caminhada ao ar livre para A aproveitar.",
    "B ajustou o travesseiro de A para ele dormir melhor.",
    "B pediu a A para dançarem juntos na sala.",
    "B pediu a A para dançarem juntos na sala, mas A recusou.")
var interaçãonegativaparceiroparceira = arrayOf(
    "B deixou um bilhete carinhoso para A encontrar, mas A ignorou.",
    "B escolheu uma playlist romântica para tocar à noite, mas A desligou o som.",
    "B comprou um presente surpresa para A, mas A não abriu.",
    "B planejou uma caminhada ao ar livre para A, mas A recusou sair.",
    "B ajustou o travesseiro de A para ele dormir melhor, mas A desfez o ajuste.")
class Player{
    var clip : Clip = AudioSystem.getClip()
    fun diminuir(){
        var volume = clip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
        volume.value -= 20.0f
    }
    fun mudar(musica: String){
        this.stop()
        this.play(musica)
    }
    fun play(musica:String){
        var music = File(musica)
        var audiostrea = AudioSystem.getAudioInputStream(music)
        clip.open(audiostrea)
        clip.start()

    }
    fun stop(){
        clip?.close()
    }}

var comidas = arrayOf(comida("Pizza",10),comida("Estrognofe com feijão", 10), comida("Hamburguer",10),comida("Chocolate", 20), comida("Coxinha",20), comida("Maçã",30), comida("Pera", 30), comida("Arroz e Feijão", 40), comida("Salada",50),
    comida("Estrogonofe", 100), comida("Ovo mole", 100)
)
var bloqueio = 1
// criar sistema de ações
// criar sistema de família
// criar sistema de estação

// aniversario quando seed for igual ao diaadia
var gochistama = mutableListOf<String>()
val palaVelhice = arrayOf(
    "tomar remédios",
    "reclamar do tempo",
    "contar histórias antigas",
    "jogar dominó",
    "fazer tricô",
    "assistir novela",
    "dormir após o almoço",
    "ir à consulta médica",
    "reclamar das dores nas costas",
    "aconselhar os mais jovens",
    "fazer caminhada leve",
    "cuidar de plantas",
    "resolver palavras cruzadas",
    "acompanhar noticiário",
    "guardar sacolas plásticas",
    "usar óculos de leitura",
    "alimentar pássaros no parque",
    "jogar baralho com amigos",
    "fazer hidroginástica",
    "visitar farmácia regularmente",
    "guardar moedas em cofrinhos",
    "contar histórias da juventude",
    "ter conversas pelo telefone fixo",
    "participar de coral da terceira idade",
    "frequentar clube do aposentado",
    "tomar sol na praça",
    "fazer artesanato",
    "economizar água e luz",
    "ler jornal impresso",
    "dar conselhos não solicitados",
    "reclamar da tecnologia moderna",
    "comparar preços de supermercado",
    "fazer sopas caseiras",
    "guardar embalagens de plástico",
    "medir pressão arterial",
    "tirar soneca à tarde",
    "trocar receitas medicinais",
    "assistir programas antigos",
    "cuidar dos netos",
    "fazer compras em feira livre",
    "participar de bingo",
    "fazer chá de ervas",
    "comentar sobre mudanças no bairro",
    "colecionar porcelanas",
    "usar chinelos em casa",
    "tomar chá à tarde",
    "passar remédios caseiros",
    "frequentar missa ou culto",
    "alimentar animais de estimação",
    "reler livros antigos",
    "organizar álbuns de fotografia",
    "passar roupa com perfeição",
    "regar plantas em horários específicos",
    "dar milho às pombas",
    "fazer caminhada matinal",
    "reclamar do barulho da vizinhança",
    "mexer em baú de lembranças",
    "fazer lista de compras detalhada",
    "conversar com vizinhos na calçada",
    "fazer pão caseiro",
    "usar bengala",
    "marcar consultas médicas",
    "separar medicamentos por horário",
    "assistir documentários históricos",
    "jogar paciência",
    "fazer terapia",
    "tomar vitaminas",
    "fazer fisioterapia",
    "visitar cemitério",
    "arrumar a casa várias vezes",
    "ouvir rádio AM",
    "consertar objetos quebrados",
    "assistir programas de auditório",
    "manter rotina rigorosa",
    "falar sobre o clima",
    "fazer conservas",
    "conferir resultados de exames",
    "participar de excursões para terceira idade",
    "usar filtro solar em excesso",
    "guardar recortes de jornal",
    "colecionar selos ou moedas",
    "usar relógio de pulso tradicional",
    "fazer aulas de dança sênior",
    "usar roupas de lã mesmo no calor",
    "assistir a missas na TV",
    "comentar sobre comportamento dos jovens",
    "aproveitar descontos para idosos",
    "fazer compras em pequenas quantidades",
    "preparar doces para os netos",
    "manter álbum genealógico",
    "conversar com plantas",
    "colecionar imãs de geladeira",
    "fazer lista de aniversários",
    "telefonar para familiares distantes",
    "fazer revisão do carro antigo",
    "usar chapéu para sair de casa",
    "revisar testamento",
    "participar de associação de bairro"
)
val palaInfancia = arrayOf(
    "fazer bagunça",
    "matar aula",
    "jogar bola na rua",
    "andar de bicicleta",
    "brincar de pega-pega",
    "construir cabana com lençóis",
    "subir em árvores",
    "jogar videogame",
    "trocar figurinhas",
    "brincar de esconde-esconde",
    "pular amarelinha",
    "jogar queimada",
    "desenhar na calçada com giz",
    "brincar de carrinho",
    "fazer guerra de travesseiros",
    "sonhar que comeu doce escondido",
    "pular em poças de água",
    "brincar de casinha",
    "fazer coleção de pedras",
    "brincar com slime",
    "assistir desenhos animados",
    "desenhar no caderno da escola",
    "fazer bagunça em casa",
    "brincar com massinha",
    "contar histórias de terror",
    "fazer piquenique no quintal",
    "brincar de médico",
    "fazer teatrinho de fantoche",
    "colecionar brinquedos surpresa",
    "jogar bolinha de gude",
    "brincar na chuva",
    "fazer castelos de areia",
    "brincar de escolinha",
    "fazer pulseiras de amizade",
    "contar piadas bobas",
    "inventar uma língua secreta",
    "brincar com balões de água",
    "fazer careta no espelho",
    "fingir ser super-herói",
    "desenhar no vidro embaçado",
    "caçar insetos",
    "participar de guerra de almofadas",
    "brincar de detetive",
    "contar estrelas à noite",
    "fazer aviões de papel",
    "brincar de mímica",
    "inventar apelidos engraçados",
    "fazer comidinhas de mentirinha",
    "colecionar tampinhas",
    "trocar bilhetinhos na sala de aula",
    "pular corda",
    "brincar de telefone sem fio",
    "fazer arte com folhas secas",
    "brincar com bexigas",
    "construir cidades de blocos",
    "brincar de roda",
    "fazer competições de corrida",
    "cantar músicas infantis",
    "imitar personagens de TV",
    "brincar de adivinhação",
    "fazer armadilhas de brinquedo",
    "jogar batalha naval",
    "fazer experiências simples",
    "brincar de discoteca",
    "inventar histórias malucas",
    "fazer clubinho secreto",
    "brincar de estátua",
    "desenhar com os dedos",
    "fazer torneio de videogame",
    "brincar com bolhas de sabão",
    "escrever diário secreto",
    "fazer show de talentos",
    "brincar de caça ao tesouro",
    "espiar os vizinhos",
    "fazer brincadeiras com água",
    "sonhar que comeu sorvete até doer a barriga",
    "desenhar no corpo com caneta",
    "fazer festinha do pijama",
    "criar fantasia com roupas velhas",
    "brincar com animais de estimação",
    "jogar jogo da velha",
    "fazer caretas para fotos",
    "apostar corrida subindo escadas",
    "colecionar cards de personagens",
    "inventar receitas malucas",
    "fazer fogueira (supervisionada)",
    "brincar de faz de conta",
    "jogar peteca",
    "fazer acampamento no quintal",
    "trocar segredos com amigos",
    "vestir roupas dos pais",
    "brincar com walkie-talkie",
    "fazer festa de aniversário para bonecos",
    "criar histórias em quadrinhos",
    "fingir ser um pirata",
    "construir forte com almofadas",
)

var eventos = arrayOf("Normal", "Natal", "Ano novo")
var estados = arrayOf("Normal","Feliz","Nervoso","Dormindo","Hibernando","Romântico")
var estações = arrayOf("Verão", "Outono","Inverno","Primavera")
var palaBoa = arrayOf("curtiu", "gostou de","amou","adorou","apreciou")
var palaAcoMorte = arrayOf("comendo miojo",
    "assaltando um banco",
    "assistindo Fórmula 1",
    "jogando videogame",
    "dormindo no sofá",
    "tomando café gelado",
    "correndo na chuva",
    "lendo um livro velho", "assistindo One Piece",
    "andando de bicicleta",
    "comprando pão quente",
    "cantando no chuveiro",
    "assistindo série antiga",
    "fazendo um bolo torto",
    "pulando na piscina",
    "escrevendo uma carta",
    "olhando o celular",
    "comendo pizza fria",
    "dançando sozinho",
    "tirando uma soneca",
    "lavando a louça",
    "jogando bola na rua",
    "vendo o pôr do sol",
    "tocando violão desafinado",
    "comendo brigadeiro",
    "passeando com o cachorro",
    "tentando cozinhar algo",
    "assistindo um filme ruim",
    "andando de skate",
    "tomando sorvete derretido",
    "olhando as estrelas",
    "fazendo uma ligação",
    "comendo pipoca queimada",
    "jogando baralho",
    "lendo notícias velhas",
    "correndo atrás do ônibus",
    "pintando um quadro feio",
    "dormindo no chão",
    "comendo sushi caro",
    "assistindo ao futebol",
    "andando sem rumo",
    "tomando banho frio",
    "escrevendo um poema",
    "fazendo careta no espelho",
    "comendo pastel na feira",
    "pulando corda",
    "vendo vídeo engraçado",
    "lavando o carro",
    "jogando xadrez mal",
    "comendo chocolate quente",
    "dançando na sala",
    "olhando fotos antigas",
    "tentando consertar algo",
    "assistindo novela chata",
    "comendo salgadinho velho",
    "andando de patins",
    "tomando chá amargo",
    "lendo um gibi",
    "correndo no parque",
    "dormindo na rede",
    "comendo churrasco",
    "assistindo documentário",
    "jogando pingue-pongue",
    "fazendo um desenho",
    "comendo bolo seco",
    "passeando no shopping",
    "cantando uma música ruim",
    "olhando pela janela",
    "tomando suco azedo",
    "escrevendo no caderno",
    "comendo coxinha fria",
    "dançando com amigos",
    "vendo um show ao vivo",
    "lavando roupa suja",
    "jogando videogame velho",
    "comendo lasanha requentada",
    "assistindo desenho animado",
    "andando de ônibus lotado",
    "tomando cerveja quente",
    "lendo um blog qualquer",
    "correndo sem motivo",
    "pintando a parede",
    "dormindo na aula",
    "comendo hamburguer gorduroso",
    "assistindo reality show",
    "jogando bola de gude",
    "fazendo origami torto",
    "comendo frutas maduras",
    "passeando na praia",
    "cantando no karaokê",
    "olhando o trânsito",
    "tomando água com gás",
    "escrevendo um e-mail",
    "comendo batata frita",
    "dançando na rua",
    "vendo um vídeo viral",
    "lavando o quintal",
    "jogando cartas com amigos",
    "comendo tapioca quente","caindo da escada")
var palaAco = arrayOf("andar de lancha","ficar em casa", "nadar",
    "andar","andar de bicicleta","jogar videogame",
    "sonhar que comeu estrogonfe com feijão","sonhar que comeu ovo mole","acordar cedo",
    "assistir South Park", "assistir Game Of Thrones", "assistir Pingu",
    "assistir TV", "assistir Shaun: O carneiro", "sonhar que comeu comida",
    "ver o Cristiano Ronaldo", "assistir Hermes e Renato",
    "assistir a melhor série já feita... Ben 10",
    "jogar Stuart Little 3 de PS2", "ver o jogo do Corinthins",
    "ver o jogo do Palmeiras", "ver o jogo de Palmeiras X Corinthians",
    "assistir vídeos de gatos", "ver você jogando esse jogo",
    "ver você não fazendo absolutamente nada","assistir Demon Slayer",
    "sonhar que comeu no McDonald","assistir à aula do Jailson aka JaJa da careca lustrada","assistir à aula do Jailson",
    "assistir à aula do Gabriel", "assistir à aula do Gabriel aka GOAT", "assistir à aula da Aurora",
    "assistir à Fórmula 1", "escutar funk","escutar K-pop",
    "assistir Dorama","assistir um filme de terror",
    "assistir um filme chato de romance","assistir um filme de aventura",
    "ler Nelson Rodrigues","ver um filme de romance",
    "ver um filme do Pantera Negra","ver um filme do Batman",
    "ver um filme do Capitão", "assistir uma vídeo aula chata",
    "assistir uma palestra chata","escutar um podcast",
    "escutar um Nerdcast","escutar um Experiência Podcast",
    "escutar o Gustavo Guanabara falando que o Acre não existe em uma leitura de e-mails no Nerdcast",
    "assistir BBB","jogar futebol","dirigir um carro","fazer golpes",
    "dançar","ir à praia", "sonhar que comeu no Burguer King", "sonhar que comeu no OutBack",
    "cair de boca no rango","participar do Proa", "resolver um Sudoku",
    "jogar Xadrez","dormir cedo","acordar cedo",
    "não fazer nada o dia inteiro","ler um livro","sonhar que comeu pastel na feira",
    "assistir um compilado de dribles do Neymar de moicano no Santos ",
    "jogar Pókemon no Nintendo DS","jogar no Nintendo 3DS",
    "ver memes por alguns minutos e perceber que já se passaram dias",
    "assistir Senhor dos Anéis","assistir Hobbit","ir ao parque",
    "assistir uma live na Twitch", "tomar café sem açucar",
    "sonhar que comeu brigadeiro de panela","ver o pôr do sol","peidar no elevador mas ninguém estava lá",
    "peidar no elevador mas o elevador estava cheio", "assistir Evangelion",
    "assistir Cowboy Bebop","assistir Ghost in the Shell","assistir Viagem de Chiriro",
    "assistir O Castelo Animado","assistir Corra","assistir algo inteligente, não entender nada mas finger que entendeu",
    "ir na terapia","ser diagnosticado como louco na terapia","assistir One Piece",
    "escutar sertanejo universitário","ficar olhando os lugares que nunca vai visitar no Google Earth","sonhar que comeu pipoca de cinema",
    "assistir Dragon Ball Z","assistir Naruto","jogar Among Us","assistir Tik Tok até que a morte nos separe",
    "ver memes do infiel Ney","gastar todo meu dinheiro no tigrinho","ganhar dinheiro no cassino",
    "escutar MPB","dançar em casa sozinho","dormir comendo chocolate","beber café frio","escutar rock dos anos 80","sonhar que comeu coxinha","sonhar que comeu sushi","sonhar que comeu cachorro quente",
    "assistir um show ao vivo","assistir um show ao vivo... pelo YouTube","fazer um bolo","destruir um bolo sem querer","fingir que está dormindo para não dar o lugar para uma senhora",
    "cagar no mato","mijar na parede de casa","cagar na praia","mijar no mar","fazer cocô no mar",
    "xingar no trânsito","ser uma boa pessoa","ser uma má pessoa","jogar Super Mario 64 DS","escutar MPBosta","assistir Ben 10","assistir Ben 10: Força Alienígena","assistir Ben 10: Supremacia Alienígena","assistir Ben 10 Ominiverse",
    "coçar a bundar","atacar a geladeira de manhã","assistir Bob Esponja","assistir Phineas e Ferb","assistir Incrível Mundo de Gumball","assistir Hora de Aventura","assitir Apenas um Show","sonhar que comeu pastel de vento","sonhar que comeu pudim de pote",
    "sonhar que comeu bolo de caneca", "colocar um ovo no microondas e vê-lo explodir","jogar Mario Kart Wii","assistir Avartar: O último Mestre do Ar","assistir Avatar: A Lenda de Korra","sonhar que comeu pizza fria de manhã","sonhar que comeu pizza de mussarela","sonhar que comeu pizza de baiacatu",
    "sonhar que comeu pizza de atum","ver o instagram até constatar que sou feio","ver o instagram até não saber se sou ou é o Cristiano Ronaldo pensando","ver o instagram até confundir meu nome com o do Neymar","assistir Harry Potter", "jogar freefire","jogar counter-strike","jogar League of Legends","jogar DOTA","assistir masterchef e achar que fazia melhor",
    "escutar pagode","escutar Tati Quebra Barraco","escutar zeca pagodinho","assistir Todo Mundo Odeia o Chris","assistir Eu, a Patroa e as Crianças","tomar banho frio",
    "assistir Toy Story","assistir matrix e se sentir o The Choosen","sonhar que comeu algodão doce","jogar Bomberman e si explodir","jogar Candy Crush","jogar Subway Surfers",
    "jogar Tetris","jogar Sonic Mania","jogar Cuphead","assistir Jumanji","assistir Velozes e Furiosos","fazer rachas","bater o carro em um racha",
    "assistir Piratas do Caribe","visitar xique-xique na Bahia","participar do GGB (Grupo Gay da Bahia)","visitar a França","visitar Portugal","visitar o Japão",
    "visitar a Inglaterra","visitar o Caribe","visitar a Itália","visitar o Vaticano","visitar Israel","visitar a Palestina","jogar Pac-Man e culpar os fantasmas","jogar Pac-Man",
    "assistir X-Men:Evolution","assistir o clipe do Michael Jackson e rir com o \"Michael, Michael, eles não ligam pra gente\"","jogar GTA San Andreas e só andar de bicicleta",
    "jogar Donkey Kong e perder todas as vidas","assistir Madagascar","sonhar que comeu milho assado","tentar fazer flexão","assistir Era do Gelo","sonhar que comeu bolo de chocolate com café","sonhar que comeu churrasco e exagerar na farofa","escutar rádio FM e torcer pra tocar algo bom",
    "escutar reggae","jogar Street Fighter","sonhar que comeu tapioca com manteiga","jogar Resident Evil e gritar com os zumbis","jogar Animal Crossing","sonhar que comeu lasanha requentada","assistir Up: Altas Aventuras","comer esfirra","tentar aprender receita no TikTok e falhar","comer esfirra de carne","comer esfirra de queijo","comer esfirra",
    "jogar Super Smash Bros","assistir Kung Fu Panda","assistir Shreak","assistir Shreak 2","assistir Shreak: Felizes para Sempre","escutar trilha sonora de filme no banho","assistir Procurando Nemo e querer ser um peixe","jogar Portal")
var palamal = arrayOf("não gostou de","odiou","não curtiu", "detestou")
var dia = 0
// o tama pode fugir se estiver triste
// sistema de rivalidade
// eventos aleatórios
//colocar os estados ausentes como null
var estoque = mutableListOf<comida>()
var diaadia = Random.nextInt(1,101)
var regi = mutableListOf<tama>()
var famili = mutableListOf<tamo.gochi>()
data class atributos(val variavel : String ,val valor : String)
data class tama(val Nome: String, val idade: Int, val frase : String,val anomorte:Int? = 0, val anonasci: Int, val icon : String, val Vivo :Boolean, var causadamorte: String? = "Morte morrida",val parceira: String? = "não teve",val Pais:String? = "Não sabe", var filho: MutableList<String>? = null,var analisedavida : String = "Teve uma vida feliz", var fugir : Boolean )
data class comida (val nome : String,var qualidade: Int)

class tamo(tamanho : Boolean, colorido : Boolean) {
    var tamanho = tamanho; var colorido = colorido;var periododoano = 0; var estação = estações[periododoano]; var evento = "Normal"
    var es = 30; var dinheiro = 0; var dinhero = 0
    var p = "\\ ^ / ".repeat(es)+"\n"+"< O > ".repeat(es)+"\n"+"/ v \\ ".repeat(es)
    var v = " | ".repeat(es)+"\n"+"- O - ".repeat(es)+"\n"+" | ".repeat(es)
    var o = " | ".repeat(es)+"\n"+"/|\\ ".repeat(es)+"\n"+ "\\|/ ".repeat(es)
    var i = "o o ".repeat(es)+ "\n" + " o ".repeat(es) + "\n" +"o ".repeat(es)
    var introestação = mutableListOf<String>(v,o,i,p);var intro = ""
    var mapadecomida = false; var rastreadordecomida = false
    fun eventospecial(){// colocar no tama
        if (estação == "Inverno" || estação == "Verão"){
            var numeroaleatorio = Random.nextInt(1,32)
            if (numeroaleatorio == 24 || numeroaleatorio == 25){
                evento = eventos[1]
            }else if (numeroaleatorio == 31){
                evento = eventos[3]
            }
        }

    }
    fun tempo(){
if(periododoano < introestação.size){
   estação =  estações[periododoano]
    intro = introestação[periododoano]
    periododoano++
}else{
    periododoano = 0
    estação =  estações[periododoano]
    intro = introestação[periododoano]
}
    }
    inner class gochi(){
        var pasta1normal: String? = null; var pasta2feliz: String? = null; var pasta3nervoso : String?= null; var pasta4dormindo: String? = null; var pasta5hibernando :String? = null; var pasta6romantico : String? = null
        var identificador = Random.nextInt(1000); var rivalnome = ""; var rivalidentificador = 0
        var v = 100; var f = 100; var fe = 100; var s = 100;var gch = "";var anonasci = 0; var seed = 0;var vivo = true;var numeroemo : Int? = null;var estado = "Feliz"; var anomorte : Int? = null
        var bv = v; var bf = f; var bfe = fe; var bs = s;var parceira : gochi? = null; var luto : Boolean = false; var ativo = true; var probageral = 0.3; var rivalseed = 0; var nomeparceira = ""
        var idade = 0; var nome = "";var rival = ""; var rivalidade = false; var customintesidade: String = ""; var desenho = false; var Pais = "Desconhecido"; var custominten = false
        var frases = mutableListOf<String>(); var chancedeengravidar = 0; var intros = mutableListOf<String>() ; var intro = ""; var doente = false; var remedio = false
        var janela : janela? = null; var carinho = false ;var iniciardoença = 0; var danodoenca = 0;var danoconstante = false;var videojogo = false;var convideojogo = 0
        var vacina = false; var acariciador = false; var bolodeprestigio = false; var brinquedo = false; var acordomorte = false; var doencacontagiosa = false; var doencagenetica = false; var filhos  = mutableListOf<String>()
        var fugiu = false
        var condoenca = 0; var idparceira = 0; var idpais = mutableListOf<Int>(); var idfilho = mutableListOf<Int>()
        fun doente() {
            if (iniciardoença == 0){
                println("$nome ficou doente")
                danoconstante = Random.nextBoolean()}
            if (iniciardoença == 0 || !danoconstante){
                if(idade < 4){
                    danodoenca = Random.nextInt(0,3)
                }else if (idade < 14){
                    danodoenca = Random.nextInt(0,6)
                }else{
                    danodoenca = Random.nextInt(0,11)
                }
            }
            if(doente){
                bv -= danodoenca
                if ( iniciardoença > 1){
                    var cura = Random.nextInt(101)
                    var nãofunciona = idade*2
                    if (cura > nãofunciona){
                        println("$nome foi curado")
                        condoenca--
                        if(condoenca ==0){
                        doente = false}
                        iniciardoença = 0
                    }else if (cura == danodoenca){
                        println("A doença de $nome piorou")
                        danodoenca *=2
                        bv -= danodoenca
                    }else{
                        println("$nome continua doente")
                    }}
                iniciardoença ++
            }
        }
        fun acao (){
            var palavra = if (diaadia <= bfe) palaBoa[Random.nextInt(0,palaBoa.size)] else palamal[Random.nextInt(0,palamal.size)]
            var ato = ""
            var frase = ""
            while (true){

                if (diaadia == 66){
                    frase = "O(a) $nome teve aula com o Júlio e quase chorou"
                    bfe -= 7
                    break
                }else if (diaadia == 61){
                    frase = "O(a) $nome recebeu o Jaílson em casa"
                    if(diaadia <= bfe) bfe -= 7 else bfe -= 3
                    break
                }else if (diaadia == 77){
                    frase = "O(a) $nome recebeu o Gabriel em casa"
                    if(diaadia <= bfe) bfe += 3 else bfe -= 3
                    break
                }else if (idade <4){
                    ato = palaInfancia[Random.nextInt(0,palaInfancia.size)]
                }else if (idade < 14){
                    ato = palaAco[Random.nextInt(0,palaAco.size)]
                }else{
                    ato = palaVelhice[Random.nextInt(0,palaVelhice.size)]
                }
                frase = "O(a) $nome $palavra $ato"
                break
            }
            println(frase)
            frases.add(frase)
        }

        fun romance(){
            var numeroaleatorio : Int
            if (estado == estados[5]){
                numeroaleatorio = Random.nextInt(1,(seed+1*1.5).toInt())
            }else{
                numeroaleatorio = Random.nextInt(1,seed+1)}
            if ( famili.size <= 4){
                if ( parceira == null ){
                    if (numeroaleatorio <= bs){
                        println("$nome encontrou uma parcera(o)")
                        bfe += 10
                        parceira = gochi()
                        parceira!!.nomeparceira = nome
                        parceira!!.ativo = false
                        parceira!!.idparceira = identificador
                        idparceira = parceira!!.identificador
                    }}else{
                    var realchancedeengravidar = if (parceira!!.chancedeengravidar == 0) 0 else (parceira!!.chancedeengravidar + chancedeengravidar)/2
                    if (Random.nextInt(1,101) <= realchancedeengravidar){
                        var filho = gochi()
                        if (filho.gch == gch || parceira!!.gch == filho.gch){
                            println("$nome e ${parceira!!.nome} tiveram um filho")
                            filhos.add(filho.nome)
                            parceira!!.filhos.add(filho.nome)

                            bfe += 10
                            parceira!!.bfe += 10
                            filho.idpais.add(identificador)
                            filho.idpais.add(parceira!!.identificador)
                            idfilho.add(filho.identificador)
                            parceira!!.idfilho.add(filho.identificador)
                            filho.Pais = "$nome e ${parceira!!.nome}"
                        if(parceira!!.doencagenetica||doencagenetica){
                            filho.doencagenetica = true
                        }
                        }else{
                            filho.idade = Random.nextInt(0,5)
                            println("$nome e ${parceira!!.nome} adotaram uma criança")
                            bfe += 10
                            filho.Pais = "Pais adotivos: $nome e ${parceira!!.nome}"
                        }
                    }}
            }
        }
        fun rivalidade() {
            var index = 0
            while (index in 0 until famili.size) {
                var x = famili.get(index)
                index++
                if (x != this) {
                    if (rival == null) {

                        if (seed == x.seed) {
                            rival = x.gch
                            rivalidade = true
                            x.rivalidade = true
                            rivalseed = x.seed
                            rivalnome = x.nome
                            rivalidentificador = x.identificador
                        }
                    } else if (!rivalidade) {
                        if (x.gch == rival) {
                            rivalidade = true
                            x.rivalidade = true
                            rivalseed = x.seed
                            rivalnome = x.nome
                            rivalidentificador = x.identificador
                        } else if (x.seed == rivalseed && rival == x.gch && rivalnome == x.nome && x.identificador == rivalidentificador) {
                            var numeroaleatorio = Random.nextInt(0, 101)
                            var numeroataque = Random.nextInt(1, seed + 1)
                            if (numeroaleatorio < numeroataque) {
                                x.bv -= 10
                                println("O $nome bateu no ${x.nome}")
                            } else if (numeroaleatorio == 100) {
                                famili.remove(x)
                                println("O $nome matou o ${x.nome}")

                            }
                        }
                    }
                }


            }
        }

        //var estados = arrayOf("Normal","Feliz","Nervoso","Dormindo","Hibernando","Romântico")
        fun emotroca(){
            println(estado)
if(estado == estados[0]){
    if(pasta1normal != null){
        janela!!.mdg(pasta1normal!!)
    }
}
            if (estado == estados[1]){//colocar mudar() a imagem do estado. ("Normal","Feliz","Nervoso","Dormindo","Hibernando","Romântico")
                if (pasta2feliz != null){
                    janela!!.mdg(pasta2feliz!!)}else{
                }
            }else if (estado == estados[2]){
                if (pasta3nervoso != null){
                    janela!!.mdg(pasta3nervoso!!)}else{
                }

            }else if (estado == estados[3]){
                if (pasta4dormindo != null){
                    janela!!.mdg(pasta4dormindo!!)}else{
                }
            }else if (estado == estados[4]){
                if (pasta5hibernando != null && bf > 50){
                    janela!!.mdg(pasta5hibernando!!)}else{
                }
            }else if (estado == estados[5]){
                if (pasta6romantico != null){
                    janela!!.mdg(pasta6romantico!!)}else{
                }
            }
        }
        fun emocao(){
            var personalidade = Random.nextInt(0, 101)
            if( pasta1normal != null){
                estado = if (seed < personalidade) estados[Random.nextInt(0,estados.size)] else estados[0]}
            else{
                estado = estados[Random.nextInt(0,estados.size)]
            }
            if(estado == estados[1] || estado == estados[2]){
                estado = if(personalidade < bfe) estados[0] else estados[1]
            }
            emotroca()
        }
        var analisedavida : String? = null
        fun fugir(){
            fugiu = true
            var fugir = Random.nextInt(0,101)
            if (fugir == 0){
                tocador.mudar(musicasJogo[9])
                println("O $nome fugiu para casa do Gabs. Ele acabou morrendo pela metralhadora de piadas sem graça do Gabriel porque não levantou no ônibus para uma menina")
                vivo = false
                anomorte = dia
                readln()

            }
            else if(fugir == 66 || fugir == 61){
                vivo = false
                anomorte = dia
                causadamorte = "O(a) $nome vendo uma careca lustrada"
                tocador.mudar(musicasJogo[Random.nextInt(9,11)])
                println("O $nome fugiu para casa do JaJa. Ele acabou morrendo de desgosto e suas últimas palavras foram: 'CARECA,CARECA,CARECA'")
                readln()

            } else if (fugir == 1){
                var numeroaleatorion = Random.nextInt(0,musicasJailson.size)
                tocador.mudar(musicasJailson[numeroaleatorion])
                vivo = false
                anomorte = dia
                causadamorte = "O(a) $nome morreu Descobrindo a verdade"
                janela!!.mdg("./Assets/finaJailson/1/1.png")
                println("O $nome fugiu para casa do JaJa. Depois de anos juntos, a máscara da mentira foi quebrada e sua face foi beijada pela verdade, nos últimos minutos de vida, ele disse: 'O Jaílson é muito melhor que o Gabriel e ele é o melhor professor do Pr... de todos os Senacs':" +
                        "\nAqui é o JaJa, o dono da caneta, Chego pesado, te deixo na treta. Sua lista da pureza não é nada, Só fachada, tua moral tá quebrada.\n" +
                        "\n" +
                        "Já era, acabou, o JaJa te detonou, Com cada verso, o palco inteiro ecoou. Gabs, meu chapa, tá na hora de aceitar, No código e no flow, você não vai me alcançar.\n" +
                        "\n" +
                        "Eu sou o Jailson, o mestre da codificação, Te deixo perdido, sem direção. Enquanto eu brilho, você só apaga, Na batalha, meu nome é quem se propaga.")
                analisedavida = "O(a) $nome teve uma vida de revelações"
                readln()

            }else if ( fugir <= 50){
                tocador.mudar(musicasJogo[8])
                println("O $nome fugiu para casa do Gabs. Ele teve uma vida feliz e saudável")
                readln()

analisedavida = "Teve uma vida feliz"
            }else if (fugir == 100){
                var numeroaleatorion = Random.nextInt(0,musicasGabriel.size)
                tocador.mudar(musicasGabriel[numeroaleatorion])
                vivo = false
                anomorte = dia
                causadamorte = "O(a) $nome morreu por ser feliz demais"

                causadamorte = "Ser feliz demais"
                println("O $nome fugiu para casa do Gabs. Eles viveram felizer para sempre e nos seus últimos momentos cantaram em uma só voz: " +
                        "\n" +
                        "Hino do Gabriel\nÉ a tropa do Gabs, chegou pra dominar, \n" +
                        "Na lista da pureza,não tem erro, ninguém vai nos parar. \n" +
                        "Mulheres primeiro, respeito é o lema, \n" +
                        "Enquanto os bobos do JaJa só criam problema. \n" +
                        "\n" +
                        "Tropa do Gabs, só meia dúzia de atividades pra te humilhar, \n" +
                        "Enquanto os do Jailson, 40 pra chorar. \n" +
                        "Aqui é estratégia, aqui é visão, \n" +
                        "Tropa do Gabs, sempre na missão. \n" +
                        " \n" +
                        "Os garotos de programa do Gabs são brabos, pode acreditar, \n" +
                        "E as garotas de p... Nem vou falar, só dá pra admirar. \n" +
                        "Quando o Gabs entra na metralhadora de piada, \n" +
                        "Cai fora, Jajá, sua turma foi derrotada. \n" +
                        "\n" +
                        "Tropa do Gabs, só meia dúzia de atividades pra te humilhar, \n" +
                        "Enquanto os do Jailson, 40 pra chorar. \n" +
                        "Na lista da pureza, tamo no topo, \n" +
                        "Jailson e os bobos, só ficam no sufoco. \n" +
                        " \n" +
                        "A turma do Jailson só toma surra programada, \n" +
                        "Enquanto o Gabs comanda, a vitória é garantida. \n" +
                        "Respeita o Gabs, que aqui é só talento, \n" +
                        "Tropa do Gabs, ninguém pode deter. ")
                analisedavida = "O(a) $nome teve uma vida boa demais feliz"

                readln()
            }else{
                tocador.mudar(musicasJogo[6])
                println("O $nome fugiu para casa do JaJa. Ele viveu uma vida triste e miserável")
                readln()
analisedavida = "O(a) $nome teve uma vida triste"
            }
            if(analisedavida == null){
                if(diafeliz > diatriste){
                    analisedavida = if(diafeliz > diatriste) "O(a) $nome teve uma vida feliz" else "O(a) $nome teve uma vida triste"
                }
            }
            println(analisedavida)
            famili.remove(this)
            var frase = frases[Random.nextInt(0,frases.size)]
            regi.add(tama(nome, idade,frase,anomorte,anonasci,janela!!.icon(),vivo,causadamorte, parceira?.nome ,Pais,filhos,analisedavida!!,fugiu))
            var index = 0
            while(index < famili.size){
                var tama = famili.get(index)
                tama.luto = true
                if(tama.pasta3nervoso != null){
                    tama.estado = estados[2]
                    emotroca()
                }
            }
        }
        init {




            var caminho = gochistama[Random.nextInt(0,gochistama.size-bloqueio)]
            var caminhotamas = File(caminho).listFiles()

            caminhotamas.forEach { pasta ->
                var chamado: String? = null
                for (x in pasta.name) {
                    if (x.isDigit()) {
                        println(chamado)
                        if(chamado == null) chamado = ""
                        chamado += x
                        println(chamado)

                    } else {
                        break
                    }

                }
                var realchamado = pasta.path
                if(pasta.name.uppercase() == "INTRO"){
                    intro = realchamado
                }else if (chamado != null) {
                    var chamadonum = chamado.toString().toInt()

                    if (chamadonum == 1) {
                        pasta1normal = realchamado
                    } else if (chamadonum == 2) {
                        pasta2feliz = realchamado
                    } else if (chamadonum == 3) {
                        pasta3nervoso = realchamado
                    } else if (chamadonum == 4) {
                        pasta4dormindo = realchamado
                    } else if (chamadonum == 5) {
                        pasta5hibernando = realchamado
                    } else if (chamadonum == 6) {
                        pasta6romantico = realchamado
                    }
                }
                if (pasta.name == "config.txt") {
                    var atributoss = mutableListOf<atributos>()

                    pasta.forEachLine { frase ->
                        var chamada: String? = null
                        var numero: String? = null
                        var palavra: String? = null
                        var chamadobol = true
                        var numerobol = false
                        var palavrabol = false
                        var parentese = false
                        for (x in frase) {
                            if (x == '"' || parentese){
                                if ( x == '"'){
                                    if (parentese){
                                        parentese = false
                                    }else{
                                        parentese = true
                                    }
                                }else{
                                    if (palavra == null) {
                                        palavra = ""
                                    }
                                    palavra+= x
                                }
                            }else if (x == '=' || chamadobol == false) {
                                chamadobol = false
                                if (x.isDigit() && !palavrabol) {
                                    if (numero == null) {
                                        numero = ""
                                    }

                                    numero += x
                                    numerobol = true

                                } else if (x.isLetter() && !numerobol) {
                                    if (palavra == null) {
                                        palavra = ""
                                    }
                                    palavra += x
                                    palavrabol = true

                                }
                            } else if (x.isLetter() && chamadobol) {
                                if (chamada == null) {
                                    chamada = ""
                                }
                                chamada += x

                            }


                        }
                        var valor: String? = null
                        if(numero != null || palavra != null){
                            if (palavrabol) {
                                valor = palavra!!
                            } else {
                                valor = numero!!
                            }}
                        if(chamada != null || valor != null){
                            atributoss.add(atributos(chamada!!, valor!!))}
                    }
                    for (x in atributoss) {
                        if (x.variavel.uppercase().replace(" ", "") == "VIDA") {
                            var realnumber = x.valor.toInt()
                            v = realnumber!!
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "FOME") {

                            var realnumber = x.valor.toInt()
                            f = realnumber!!
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "FELICIDADE") {

                            var realnumber = x.valor.toInt()
                            fe = realnumber!!
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "SEXAPPEL") {

                            var realnumber = x.valor.toInt()
                            s = realnumber!!
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "GOCHI") {

                            gch = x.valor
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "RIVAL") {

                            rival = x.valor
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "INTENSIDADE") {
                            custominten = true
                            customintesidade = x.valor
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "DESENHO") {

                            desenho = x.valor.toBoolean()
                        }




                    }

                }


            }
            var fileintro = File(intro).listFiles().filter { it.extension == "wav" }
            fileintro.forEach(){
                    audio -> intros.add(audio.path)
            }
            var audioreal = intros[Random.nextInt(0,intros.size)]

            anonasci = dia
            idade = 0
            seed = Random.nextInt(1,diaadia+1)

            bv = v - ((v * probageral).toFloat() * (seed / 100).toFloat()).toInt()
            bf = f - ((f * probageral).toFloat() * (seed / 100).toFloat()).toInt()
            bfe = fe - ((fe * probageral).toFloat() * (seed / 100).toFloat()).toInt()
            bs = s - ((s * probageral).toFloat() * (seed / 100).toFloat()).toInt()
if(diaadia%10 == 0){
    doencagenetica = true
}

            numeroemo = (seed/10)
            if (numeroemo == 0){
                numeroemo = numeroemo!! + 1
            }else if(numeroemo!! > 10){
                numeroemo = numeroemo!! - 1
            }
            chancedeengravidar = if (seed > 20) 20 else seed
            var ap = "Nome: $nome | Idade: $idade | Vida: $v | Fome: $f"
            var numeroaleatorio = Random.nextInt(1,101)
            tamanho = if (numeroaleatorio == 100) !tamanho else tamanho
            numeroaleatorio = Random.nextInt(1,101)
            colorido = if(numeroaleatorio == 100) !colorido else colorido
            var intensidadealea = if (custominten) 10 else Random.nextInt(1,10)
            var emoinicio = pasta1normal
            if(pasta1normal != null){
                emoinicio = pasta1normal
            }else if (pasta2feliz != null){
                emoinicio = pasta2feliz
            }else if (pasta3nervoso != null){
                emoinicio = pasta3nervoso
            }else if (pasta4dormindo != null){
                emoinicio = pasta4dormindo
            }else if (pasta5hibernando != null){
                emoinicio = pasta5hibernando
            }else if (pasta6romantico != null){
                emoinicio = pasta6romantico
            }
            janela = janela(tamanho,colorido,desenho,emoinicio!!,ap,intensidadealea,customintesidade!!)
            println("Escreva o nome do TamaGochi") // colocar em um Jlabel
            nome = readln()
            janela!!.terminar()
            tocador.mudar(audioreal)
            famili.add(this)


        }

        fun come(qualidade: Int){
            comer = 0
            var numeroaleatorio = Random.nextInt(1,seed+1)
            bf += qualidade*(diaadia/100) - numeroaleatorio
            if(qualidade > 30){
                bfe+= qualidade/10
            }

        }
        fun danoFome(){
            var danofome = Random.nextInt(0,seed+1/10)
            if(carinho){
                danofome = (danofome * 1.5).toInt()
            }
            bf -= danofome
        }
        fun brincadeira(){
            carinho = true
            var numeroaleatoriobrincadeira = 0
            var afinidade = (idade/100) + 1
            if (estado == estados[1]){
                numeroaleatoriobrincadeira = Random.nextInt(1, ((seed+1)*1.5).toInt())
            }else if (estado == estados[2]){
                numeroaleatoriobrincadeira = Random.nextInt(1, ((seed+1)*0.5).toInt())

            }else{
                numeroaleatoriobrincadeira = Random.nextInt(1, seed+1)}
            bfe += numeroaleatoriobrincadeira * afinidade
            if(videojogo){
                bfe += 10 - convideojogo
            }
        }
        var causadamorte : String? = null
        fun morreu(){
            famili.remove(this)
            var momentodamorte = palaAcoMorte[Random.nextInt(0,palaAcoMorte.size-1)]
            if (causadamorte == null){
                causadamorte = "O(a) $nome morreu $momentodamorte"
                println(causadamorte)

            }
            analisedavida = if(diafeliz > diatriste) "O(a) $nome teve uma vida feliz" else "O(a) $nome teve uma vida triste"
            var frase = frases[Random.nextInt(0,frases.size)]
            println(analisedavida)
            anomorte = dia
            regi.add(tama(nome, idade,frase,anomorte,anonasci,janela!!.icon(),vivo,causadamorte,parceira?.nome,Pais,filhos,analisedavida!!,fugiu))
            var index = 0
            while(index < famili.size){
                var tama = famili.get(index)
                tama.luto = true
                if(tama.pasta3nervoso != null){
                    tama.estado = estados[2]
                    emotroca()
                }
            }
        }
        fun acaofamilia(){
            var index = 0
            while (index < famili.size){
                var numeroaleatorio = Random.nextInt(1,101)
                var tama = famili.get(index)
                if(!tama.equals(this) && numeroaleatorio in 1..10){
                    if(tama.identificador == idparceira){
                        if(diaadia < bfe && diaadia < tama.bfe){
                            println(interaçãopositivaparceiraparceiro[Random.nextInt(0,interaçãopositivaparceiraparceiro.size)].replace("B",nome).replace("A",tama.nome))
                        bfe+= 10
                            tama.bfe += 10
                        }else if (diaadia < bfe || diaadia < tama.bfe){
                            println(interaçãonegativaparceiroparceira[Random.nextInt(0,interaçãonegativaparceiroparceira.size)].replace("B",nome).replace("A",tama.nome))
                        tama.bfe -= 5
                            bfe -= 5
                        }else{
                            println(interaçãodeutudoerrado[Random.nextInt(0,interaçãodeutudoerrado.size)].replace("A",nome).replace("B",tama.nome))
                        tama.bfe -= 10
                            bfe -= 10
                        }
                    }else if (tama.identificador in idpais){
                        if(diaadia < bfe && diaadia < tama.bfe){
                            println(interaçãopositivapais[Random.nextInt(0,interaçãopositivapais.size)].replace("B",nome).replace("A",tama.nome))
                            tama.bfe += 10
                            bfe += 10
                        }else if (diaadia < bfe){
                            println(interaçãonegativapaispais[Random.nextInt(0,interaçãonegativapaispais.size)].replace("B",nome).replace("A",tama.nome))
                            tama.bfe -= 5
                            bfe -= 3
                        }else if (diaadia < tama.bfe){
                            println(interaçãonegativafilhospais[Random.nextInt(0,interaçãonegativafilhospais.size)].replace("B",nome).replace("A",tama.nome))
                            tama.bfe -= 3
                            bfe -= 5
                        } else{
                            println(interaçãodeutudoerrado[Random.nextInt(0,interaçãodeutudoerrado.size)].replace("A",nome).replace("B",tama.nome))
                            tama.bfe -= 10
                            bfe -= 10
                        }
                    }else if (tama.identificador in idfilho){
                        if(diaadia < bfe && diaadia < tama.bfe){
                            println(interaçãopositivafilhos[Random.nextInt(0,interaçãopositivafilhos.size)].replace("A",nome).replace("B",tama.nome))
                            tama.bfe += 10
                            bfe += 10
                        }else if (diaadia < bfe){
                            println(interaçãonegativafilhosfilhos[Random.nextInt(0,interaçãonegativafilhosfilhos.size)].replace("A",nome).replace("B",tama.nome))
                            tama.bfe -= 5
                            bfe -= 3
                        }else if (diaadia < tama.bfe){
                            println(interaçãonegativapaisfilhos[Random.nextInt(0,interaçãonegativapaisfilhos.size)].replace("A",nome).replace("B",tama.nome))
                            tama.bfe -= 3
                            bfe -= 5
                        } else{
                            println(interaçãodeutudoerrado[Random.nextInt(0,interaçãodeutudoerrado.size)].replace("A",nome).replace("B",tama.nome))
                            tama.bfe -= 10
                            bfe -= 10
                        }
                    }else if (tama.idpais == idpais){
                        if(diaadia < bfe && diaadia < tama.bfe){
                            println(interaçãopositivairmãoirmã[Random.nextInt(0,interaçãopositivairmãoirmã.size)].replace("B",nome).replace("A",tama.nome))
                            tama.bfe += 10
                            bfe += 10
                        }else if (diaadia < bfe || diaadia < tama.bfe){
                            println(interaçãonegativairmãoirmã[Random.nextInt(0,interaçãonegativairmãoirmã.size)].replace("B",nome).replace("A",tama.nome))
                            tama.bfe -= 5
                            bfe -= 5
                        }else{
                            println(interaçãodeutudoerrado[Random.nextInt(0,interaçãodeutudoerrado.size)].replace("A",nome).replace("B",tama.nome))
                            tama.bfe -= 10
                            bfe -= 10
                        }
                    }
                }
                index++
            }
        }

        fun dano(){
            var multiplicadorDano = 1.0
            var dano = 10 - (10*0.3)*(seed/100)
            if (estação == "Inverno") {
                multiplicadorDano = 2.0

            }else{
                multiplicadorDano = 1.0
            }
            var danoreal = dano * multiplicadorDano
            if(!acordomorte){
            bv-= (danoreal - (danoreal*(((bf+bfe)/2)/100))).toInt()}
        }
        fun danofelicidade(){
            var multiplicadorDanodefelicidade = 1.0
            var danoFelicidade = 10 - (10*0.3)*(seed/100)
            var afinidade = (idade/100) + 1
            if (estação == "Inverno") {
                multiplicadorDanodefelicidade = 2.0

            }else{
                multiplicadorDanodefelicidade = 1.0
            }
            if(idade < 4){
                danoFelicidade /= 2
                multiplicadorDanodefelicidade -= 0.5
            }else if (idade >= 14){
                danoFelicidade *= 1.5
                multiplicadorDanodefelicidade += 0.5
            }
            if (evento == eventos[1] || evento == eventos[2] && famili.size == 1){
                multiplicadorDanodefelicidade +=0.5
            }else if (evento == eventos[1] || evento == eventos[2] && famili.size > 1){
                multiplicadorDanodefelicidade -=0.5
            }
            var danoreal = danoFelicidade * multiplicadorDanodefelicidade
            if(brinquedo) {
                if(!carinho){
                    if(idade < 14){
                    bfe-= ((afinidade * danoreal*0.5 + (danoreal - danoreal*(bf/100)))/2).toInt()}else{
                        bfe-= ((afinidade * danoreal*0.5 + (danoreal - danoreal*(bf/100)))/3).toInt()}
                }else{
                    if(idade < 14){
                    bfe-= ((danoreal*0.5 + (danoreal - danoreal*(bf/100)))/2).toInt()}else{
                        bfe-= ((danoreal*0.5 + (danoreal - danoreal*(bf/100)))/1.5).toInt()
                    }
                }

            }else{
            if(!carinho){
                bfe-= (afinidade * danoreal*0.5 + (danoreal - danoreal*(bf/100))).toInt()
            }else{
                bfe-= (danoreal*0.5 + (danoreal - danoreal*(bf/100))).toInt()
            }}

        }
        var comer = 0
        var diafeliz = 0
        var diatriste = 0
        fun check(){
            var multiplasdoenças = Random.nextInt(0,(idade+1)*2)
            if(!vivo){
                morreu()
                return
            }
if(vacina){
    doente = false
    condoenca = 0
    doencacontagiosa = false
}
            if (!vacina) {
                var saude = Random.nextInt(1, 101)
                if (idade < 4 && saude in 1..10 ) {
                    doente = true
                    condoenca++
                } else if (idade < 14 && saude in 1..20) {
                    condoenca++
                    doente = true
                } else if (idade >= 14 && saude in 1..40) {
                    condoenca++
                    doente = true
                }
            }
            if(seed in 1..multiplasdoenças && doente){
                condoenca ++
                if(!doencacontagiosa && Random.nextInt(1,11) ==  1){
                    doencacontagiosa = true
                }
            }
            if(doente){
             for (doenca in 1..condoenca){
                 doente()
             }
                if(iniciardoença == 0 && Random.nextInt(1,11) ==  1){
                    doencacontagiosa = true
                }
            }
            if(doencacontagiosa && famili.size > 1){
                var index = 0
                while (index < famili.size){
                    var ficardoente = Random.nextInt(1,11)
                    if(!famili.get(index).equals(this)&& ficardoente in 1..3){

                            famili.get(index).condoenca++
                            famili.get(index).doente = true
                            famili.get(index).doencacontagiosa = true
                    }
                }
            }

            if (dia % numeroemo!! == 0) {
                emocao()
            }

            if(idade > 4){
                if (bfe <= 0){
                    fugir()
                    return
                }

                rivalidade()

                if(ativo){
                    romance()
                }

            }

            var ataquecardiaco = Random.nextBoolean()
            if (ataquecardiaco && idade > 14){
                println("Morreu de ataque cardiaco")
                morreu()
                return
            }
            if(acordomorte){
                var index = 0
                while (index < famili.size){
                    if(!famili.get(index).equals(this)){
                    var morto = Random.nextBoolean()
                    if(morto){
                        println("O(a) ${famili.get(index).nome} morreu de maneira estranha")
                        famili.get(index).morreu()
                    }
                }}
            }
if(luto){
    danofelicidade()
    luto = false
}
            if(famili.size > 1){
                acaofamilia()
            }
            var sorte = Random.nextInt(1,101)
            if(sorte in 1..10){
                println("O(a) gochi $nome encontrou ")
                dinhero++
            }else if(sorte == 1 || sorte == 100){
                println("O(a) gochi $nome encontrou muito dinheiro")
                dinhero += 3
            }
            if(doencagenetica){
                bv -= Random.nextInt(0,6)
            }
            if (bv <= 0){
                morreu()
            }else if (bv > v){
                bv = v
            }
            if (bfe > fe){
                bfe = fe
            }
            if (bf == 0){
                comer ++
            }
            if (comer == 3){
                morreu()
            }
            if (bf > f) {
                bf = f
            }
            if (bfe > 50){
                diafeliz++
            }else{
                diatriste++
            }
            acao()
            janela!!.mudarestiloexterno()
            if(diaadia == seed){
                println("Hoje é o aniversário do(a) $nome")
                bfe += 10
                if(pasta2feliz != null){
                    estado = estados[1]
                    emotroca()
                }
            }
            idade++
            janela!!.title = "Nome: $nome | Idade: $idade | Vida: $bv | Fome: $bf"
        }
        fun passardia(){
            if(remedio){
                doente = false
                remedio = false
            }
            if(vacina && idade >= 14){
                vacina = false
            }
            if(acariciador && !carinho){
                brincadeira()
            }
            if(bolodeprestigio){
                if(idade >= 14){
                    bv += v/2
                    bf += f/2
                    bfe += fe/2
                }else{
                    bv = v
                    bf = f
                    bfe = fe
                }
                bolodeprestigio = false
            }
            danoFome()
            danofelicidade()
            dano()
            check()
        }




    }

    var g : gochi? = null
    var acao = false
    fun darcomida(){
        var comidatama: comida
        println("-".repeat(80)+ "\n")
        var tamagochi : gochi
        if (estoque.size > 0){
            if( estoque.size ==1){
                println("Comida: ${estoque[0].nome}")
                comidatama = estoque[0]
            }else{
                estoque.forEachIndexed { index, comida ->
                    println("${index +1} - ${comida.nome}")
                }
                println("0 - Sair")
                println("Escolha uma comída")
                var r = readln().toIntOrNull()
                while(r == null || r-1 > estoque.size -1 || r - 1 < -1){
                    println("Escreva um número")
                    r = readln().toIntOrNull()
                }
                if(r == 0){
                    println("Sair")
                    return
                }else{
                    println("Comida: ${estoque[r-1].nome}")
                    comidatama = estoque[r-1]

                }
            }
            if(famili.size == 1){
                println("${famili[0].nome} comeu ${comidatama!!.nome}")
                tamagochi = famili[0]
            }else {
                println("Qual tama você quer alimentar")
                famili.forEachIndexed { index, gochi ->
                    println("${index + 1} - ${gochi.nome}")

                }
                println("0 - Sair")
                println("Escolha um tamagochi")
                var r = readln().toIntOrNull()
                while (r == null || r - 1 > famili.size - 1 || r - 1 < -1) {
                    println("Escreva um número")
                    r = readln().toIntOrNull()
                }
                if (r == 0) {
                    println("Sair")
                    return
                } else {
                    println("Comida: ${famili[r - 1]}")
                    tamagochi = famili[r - 1]

                }
            }
                tamagochi.come(comidatama!!.qualidade)

        }else{
            println("Não tem nada no estoque")
        }
    }

    fun procurarcomida(){
        acao = true
        var multiplicador = 0.5
        if (estação == "Verão"){
            multiplicador = 1.0
        }else if (estação =="Inverno"){
            multiplicador = 0.5
        }else if (estação =="Outono"){
            multiplicador = 0.8
        }else if (estação =="Primavera"){
            multiplicador =2.0
        }
        var numeroaleatorio = diaadia
        var numeroAletorioParaoMapa = Random.nextInt(10,21)
        if(rastreadordecomida){
            numeroaleatorio = Random.nextInt(79,101)
        }
        if(mapadecomida && diaadia + numeroAletorioParaoMapa <=100){
            numeroaleatorio +=20
        }
        var indice = comidas.size -2
        var comida : comida
        if (numeroaleatorio in 1..40){
            comida = comidas[Random.nextInt(0,indice/4)]
        }else if (numeroaleatorio == 100){
            tocador.mudar(musicasJogo[7])
            comida = comidas[Random.nextInt(indice, comidas.size)]
        }else if (numeroaleatorio in 41..80){
            comida = comidas[Random.nextInt(2,indice/2)]
        }else{
            comida = comidas[Random.nextInt(4,indice)]

        }
        comida.qualidade = (comida.qualidade * multiplicador).toInt()
        println("Você achou ${comida.nome} e guardou no estoque")
        estoque.add(comida)

    }
    var caminhotamas = File("./Assets/tamaGochi").listFiles()
    var querosair = false
    init {
        caminhotamas.forEach { pasta ->
            gochistama.add(pasta.path)
        }
        pastamusicaGabriel.forEach { arquivo ->
            musicasGabriel.add(arquivo.path)
        }
        pastamusicaJailson.forEach {arquivo ->
            musicasJailson.add(arquivo.path)
        }
        g = gochi()

        while (dia == 0 || famili.size > 0|| querosair == true){
            menu()
        }
        println("Você conseguiu Chegar à $dia")
    }

    fun menu() {
        println(intro)
        tocador.mudar(musicasJogo[periododoano+2])
        println("-".repeat(40)+"Menu"+"-".repeat(40))
        if(!acao){
            println("1"+" ".repeat(35)+"Procurar comida")
            println("2"+" ".repeat(35)+"Estoque")
            println("3"+" ".repeat(35)+"Brincadeira")
            println("4"+" ".repeat(35)+"Dar comida")
            println("5"+" ".repeat(35)+"Loja")
            println("6"+" ".repeat(35)+"Passar o dia")
            println("0"+" ".repeat(35)+"Sair")


            var resp = readln()

            when (resp) {
                "6" -> {passaardia(); tocador.mudar(musicasJogo[1])}
                "5" -> {loja(); tocador.mudar(musicasJogo[1])}
                "4" -> {darcomida();tocador.mudar(musicasJogo[1])}
                "3" -> {brincadeira(); tocador.mudar(musicasJogo[1])}
                "2"-> {estoque(); tocador.mudar(musicasJogo[1])}
                "1" -> {procurarcomida(); tocador.mudar(musicasJogo[1])}
                "0" -> {querosair = true; tocador.mudar(musicasJogo[1])}
            }}else{
            println("1"+" ".repeat(35)+"Estoque")
            println("2"+" ".repeat(35)+"Dar comida")
            println("3"+" ".repeat(35)+"Loja")
            println("4"+" ".repeat(35)+"Passar o dia")
            println("0"+" ".repeat(35)+"Sair")
            var resp = readln()

            when (resp) {
                "1" -> {estoque(); tocador.mudar(musicasJogo[1])}
                "2" -> {darcomida(); tocador.mudar(musicasJogo[1])}
                "3"-> {loja();tocador.mudar(musicasJogo[1])}
                "4" -> {passaardia(); tocador.mudar(musicasJogo[1])}
                "0" -> {querosair = true; tocador.mudar(musicasJogo[1])}

            }
        }}
    fun brincadeira(){
        println("-".repeat(80))
        estoque.forEachIndexed { index, comida ->
            println("${index + 1} - ${comida.nome}")
        }
        println("0 - Sair")
        println("Escolha um gochi para dar carinho")
        var r = readln()
        while(r.toIntOrNull() !in 0..famili.size){
            println("Escolha um gochi,somente números da lista")
            r = readln()
        }
        if(r.toInt() > 0){
        famili.get(r.toInt() - 1).brincadeira()}else{
            return
        }
        if(diaadia in 1..10){
            return
        }

    }
    fun loja(){
        println("-".repeat(80))
println("Dinheiro: $dinheiro")
        println("1"+" ".repeat(35)+"Videojogo - deixa o Gochi dependente (não foge) e deixa ele feliz, mas tem consequências   5")
        println("2"+" ".repeat(35)+"Remédio - cura qualquer doença comum até a velhice                                         5")
        println("3"+" ".repeat(35)+"Vacina - Protege o Gochi até a velhice contra doenças                                     15")
        println("4"+" ".repeat(35)+"Acariciador automático - brinca com o gochi automaticamente                               15")
        println("5"+" ".repeat(35)+"Bolo de prestígio - restaura toda a vida, fome e felicidade                               10")
        println("6"+" ".repeat(35)+"Boneco - Diminui o dano de felicidade                                                     10")
        println("7"+" ".repeat(35)+"Acordo com a  morte - Seu Gochi não toma mais dano, mas haverá consequências              20")
        println("8"+" ".repeat(35)+"Mapa de comida - Faz você ter mais chance de conseguir comidas melhores                   15")
        println("9"+" ".repeat(35)+"Rastreador de comida - Faz você conseguir só as melhores comidas                          30")
        println("0"+" ".repeat(35)+"Sair")
        var resp = readln()
        while(resp.toIntOrNull() !in 0..9){
            println("Escreva um número dentro da lista")
            resp = readln()
        }
if(resp == "1"&& dinhero - 5 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.nome}")
    }
    println("0 - Sair")
    println("Escolha um gochi para dar o videojogo")
    var r = readln()
    while(r.toIntOrNull() !in 0..famili.size){
        println("Escolha um gochi para dar o videojogo,somente números da lista")
        r = readln()
    }
    if(r.toInt() >= 1){
    index = r.toInt() -1
    famili.get(index).videojogo = true; famili.get(index).convideojogo = 0}else if (r.toInt() == 0){
        return
    }
    dinhero -= 5
}else if (resp == "2" && dinhero - 5 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.nome}")
    }
    println("0 - Sair")
    println("Escolha um gochi para dar o remédio")
    var r = readln()
    while(r.toIntOrNull() !in 0..famili.size){
        println("Escolha um gochi para dar o remédio,somente números da lista")
        r = readln()
    }
    if(r.toInt() >= 1  && !famili.get(r.toInt()-1).remedio){
        index = r.toInt() -1
        famili.get(index).remedio = true
        dinhero -= 5
    }else if (r.toInt() == 0){
        return
    }else{
        println("Você já comprou o acariciador para o gochi")
loja()
    }

}else if (resp == "3" && dinhero - 15 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.nome}")
    }
    println("0 - Sair")
    println("Escolha um gochi para dar a vacina")
    var r = readln()
    while(r.toIntOrNull() !in 0..famili.size){
        println("Escolha um gochi para dar a vacina,somente números da lista")
        r = readln()
    }
    if(r.toInt() >= 1  && !famili.get(r.toInt()-1).vacina){
        index = r.toInt() -1
        famili.get(index).vacina = true
        dinhero -= 15
    }else if (r.toInt() == 0){
        return
    }else{
        println("Você já deu a vacina para o gochi")
        loja()
    }
}else if (resp == "4" && dinhero - 15 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.nome}")
    }
    println("0 - Sair")
    println("Escolha um gochi para dar o acariciador automatico")
    var r = readln()
    while(r.toIntOrNull() !in 0..famili.size){
        println("Escolha um gochi para dar o acariciador automatico,somente números da lista")
        r = readln()
    }
    if(r.toInt() >= 1  && !famili.get(r.toInt()-1).acariciador){
        index = r.toInt() -1
        famili.get(index).acariciador = true
        dinhero -= 15
    }else if (r.toInt() == 0){
        return
    }else{
        println("Você já comprou o acariciador para o gochi")
        loja()
    }

}else if (resp == "5" && dinhero - 10 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.nome}")
    }
    println("0 - Sair")
    println("Escolha um gochi para dar o bolo de prestigio")
    var r = readln()
    while(r.toIntOrNull() !in 0..famili.size){
        println("Escolha um gochi para dar o bolo de prestigio,somente números da lista")
        r = readln()
    }
    if(r.toInt() >= 1  && !famili.get(r.toInt()-1).bolodeprestigio){
        index = r.toInt() -1
        famili.get(index).bolodeprestigio = true
        dinhero -= 10
    }else if (r.toInt() == 0){
        return
    }else{
        println("Você já deu um bolo de prestígio para o gochi")
        loja()
    }
}else if (resp == "6" && dinhero - 10 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.nome}")
    }
    println("0 - Sair")
    println("Escolha um gochi para dar o brinquedo")
    var r = readln()
    while(r.toIntOrNull() !in 0..famili.size){
        println("Escolha um gochi para dar o brinquedo,somente números da lista")
        r = readln()
    }
    if(r.toInt() >= 1  && famili.get(r.toInt()-1).brinquedo == false){
        index = r.toInt() -1
        famili.get(index).brinquedo = true
        dinhero -= 10
    }else if (r.toInt() == 0){
        return
    }else{
        println("O gochi já tem um brinquedo")
    loja()
    }
}else if (resp == "7" && dinhero - 20 >= 0){
    var index : Int
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.nome}")
    }
    println("0 - Sair")
    println("Escolha um gochi para fazer um acordo com a morte")
    var r = readln()
    while(r.toIntOrNull() !in 0..famili.size){
        println("Escolha um gochi para fazer um acordo com a morte,somente números da lista")
        r = readln()
    }
    if(r.toInt() >= 1 && famili.get(r.toInt()-1).acordomorte == false){
        index = r.toInt() -1
        famili.get(index).acordomorte = true
        dinhero -= 20
    }else if (r.toInt() == 0){
     return
    }else{
        println("O gochi já fez um acordo com a morte")
        loja()
    }
}else if (resp == "8" && dinhero - 15 >= 0) {
    if(!mapadecomida){
    println("Você comprou o mapa de comida")
    mapadecomida = true
    dinhero -= 15}else{
        println("Você já tem o mapa de comida")
    }
}else if (resp == "9" && dinhero - 30 >= 0) {
    if(!rastreadordecomida){
   println("Você comprou o rastreador de comida")
    rastreadordecomida = true
    dinhero -= 30}else{
        println("Você já tem o rastreador de comida")
    }
}
    }
    fun estoque(){
        println("-".repeat(80))

        estoque.forEachIndexed { index, comida ->
            println("${index+1} - ${comida.nome}")
        }
        readln()
    }

    fun passaardia(){
        var index = 0
        println("-".repeat(85))
        while(index < famili.size){
            famili.get(index).passardia()
            index++
        }
        dia++
        diaadia = Random.nextInt(1,100)
        if(dia % 4 == 0){
            tempo()
        }
eventospecial()
    }


}

fun main() {
    pastamusicaJogo.forEach{ arquivo ->
        musicasJogo.add(arquivo.path)

    }
    tocador.play(musicasJogo[0])
    tocador.diminuir()
    println(musicasJogo)
    var tam = false
    var color = false
    println("Você quer a jogar com janelas grandes? (S/N)")
    var r = readln()
    while (r.uppercase().replace(" ", "") != "S" && r.uppercase().replace(" ","") != "N"){
        println("Escreva somente Grande ou Pequena")
        r = readln()


    }


    when(r!!.uppercase().replace(" ", "")){
        "S" -> tam = true
        "N" -> tam = false

    }
    println("Você quer a jogar com cores? (S/N)")
    r = readln()
    while (r!!.uppercase().replace(" ", "") != "S" && r!!.uppercase().replace(" ","") != "N"){
        println("Escreva somente S ou N")
        r = readln()

    }


    when(r!!.uppercase().replace(" ", "")){
        "S" -> color = true
        "N" -> color = false

    }
    tocador.stop()
    tamo(tam,color)





}


fun titulo(){
    println("\n" +
            " ________ _______ ___ ________ ________ _______ _____ ______ ___ ___ ___ ________ ________ ________ \n" +
            "|\\ ____\\ |\\ ___ \\ |\\ \\ |\\ __ \\ |\\ __ \\ |\\ ___ \\ |\\ _ \\ _ \\ |\\ \\ / /||\\ \\ |\\ ___ \\ |\\ ___ \\ |\\ __ \\ \n" +
            "\\ \\ \\___|_ \\ \\ __/| \\ \\ \\ \\ \\ \\|\\ \\ \\ \\ \\|\\ /_ \\ \\ __/| \\ \\ \\\\\\__\\ \\ \\ ____________ \\ \\ \\ / / /\\ \\ \\ \\ \\ \\\\ \\ \\ \\ \\ \\_|\\ \\ \\ \\ \\|\\ \\ \n" +
            " \\ \\_____ \\ \\ \\ \\_|/__ __ \\ \\ \\ \\ \\ __ \\ \\ \\ __ \\ \\ \\ \\_|/__ \\ \\ \\\\|__| \\ \\ |\\____________\\ \\ \\ \\/ / / \\ \\ \\ \\ \\ \\\\ \\ \\ \\ \\ \\ \\\\ \\ \\ \\ \\\\\\ \\ \n" +
            " \\|____|\\ \\ \\ \\ \\_|\\ \\ |\\ \\\\_\\ \\ \\ \\ \\ \\ \\ \\ \\ \\|\\ \\ \\ \\ \\_|\\ \\ \\ \\ \\ \\ \\ \\ \\|____________| \\ \\ / / \\ \\ \\ \\ \\ \\\\ \\ \\ \\ \\ \\_\\\\ \\ \\ \\ \\\\\\ \\ \n" +
            " ____\\_\\ \\ \\ \\_______\\\\ \\________\\ \\ \\__\\ \\__\\ \\ \\_______\\ \\ \\_______\\ \\ \\__\\ \\ \\__\\ \\ \\__/ / \\ \\__\\ \\ \\__\\\\ \\__\\ \\ \\_______\\ \\ \\_______\\ \n" +
            " |\\_________\\ \\|_______| \\|________| \\|__|\\|__| \\|_______| \\|_______| \\|__| \\|__| \\|__|/ \\|__| \\|__| \\|__| \\|_______| \\|_______| \n" +
            " \\|_________| \n" +
            " \n" +
            " \n" +
            " ________ ________ \n" +
            " |\\ __ \\ |\\ __ \\ \n" +
            " \\ \\ \\|\\ \\ \\ \\ \\|\\ \\ \n" +
            " \\ \\ __ \\ \\ \\ \\\\\\ \\ \n" +
            " \\ \\ \\ \\ \\ \\ \\ \\\\\\ \\ \n" +
            " \\ \\__\\ \\__\\ \\ \\_______\\ \n" +
            " \\|__|\\|__| \\|_______| \n" +
            " \n" +
            " \n" +
            " \n" +
            " ___ ________ ________ ________ ________ ________ _________ ________ _____ ______ ________ ________ ________ ________ ___ ___ ___ \n" +
            " |\\ \\ |\\ __ \\ |\\ ____\\ |\\ __ \\ |\\ ___ \\ |\\ __ \\ |\\___ ___\\ |\\ __ \\ |\\ _ \\ _ \\ |\\ __ \\ |\\ ____\\ |\\ __ \\ |\\ ____\\ |\\ \\|\\ \\ |\\ \\ \n" +
            " \\ \\ \\ \\ \\ \\|\\ \\ \\ \\ \\___| \\ \\ \\|\\ \\ \\ \\ \\_|\\ \\ \\ \\ \\|\\ \\ \\|___ \\ \\_| \\ \\ \\|\\ \\ \\ \\ \\\\\\__\\ \\ \\ \\ \\ \\|\\ \\ \\ \\ \\___| \\ \\ \\|\\ \\ \\ \\ \\___| \\ \\ \\\\\\ \\ \\ \\ \\ \n" +
            " __ \\ \\ \\ \\ \\ \\\\\\ \\ \\ \\ \\ ___ \\ \\ \\\\\\ \\ \\ \\ \\ \\\\ \\ \\ \\ \\\\\\ \\ \\ \\ \\ \\ \\ __ \\ \\ \\ \\\\|__| \\ \\ \\ \\ __ \\ \\ \\ \\ ___ \\ \\ \\\\\\ \\ \\ \\ \\ \\ \\ __ \\ \\ \\ \\ \n" +
            "|\\ \\\\_\\ \\ \\ \\ \\\\\\ \\ \\ \\ \\|\\ \\ \\ \\ \\\\\\ \\ \\ \\ \\_\\\\ \\ \\ \\ \\\\\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\|\\ \\ \\ \\ \\\\\\ \\ \\ \\ \\____ \\ \\ \\ \\ \\ \\ \\ \\ \n" +
            "\\ \\________\\ \\ \\_______\\ \\ \\_______\\ \\ \\_______\\ \\ \\_______\\ \\ \\_______\\ \\ \\__\\ \\ \\__\\ \\__\\ \\ \\__\\ \\ \\__\\ \\ \\__\\ \\__\\ \\ \\_______\\ \\ \\_______\\ \\ \\_______\\ \\ \\__\\ \\__\\ \\ \\__\\\n" +
            " \\|________| \\|_______| \\|_______| \\|_______| \\|_______| \\|_______| \\|__| \\|__|\\|__| \\|__| \\|__| \\|__|\\|__| \\|_______| \\|_______| \\|_______| \\|__|\\|__| \\|__|\n" +
            " \n" +
            " \n" +
            " \n")

}


