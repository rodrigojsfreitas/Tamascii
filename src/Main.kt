import Jogo.janela
import java.io.File
import javax.sound.sampled.*
import kotlin.random.Random
var es = 12
var diaRecord = 0
var tocador = Player()
var pastamusicaJogo = File("./Assets/Músicas/Jogo/").listFiles().filter { it.extension == "wav" }.sortedBy { it.name.filter { it.isDigit() }.toInt() }
var pastamusicaGabriel = File("./Assets/Músicas/Gabriel/").listFiles().filter { it.extension == "wav" }
var pastamusicaJailson = File("./Assets/Músicas/Jailson/").listFiles().filter { it.extension == "wav" }
var musicasGabriel = mutableListOf<String>()
var musicasJailson = mutableListOf<String>()
var musicasJogo = mutableListOf<String>()
var frasemortes = arrayOf("partiu para o outro lado.","foi para o outro lado.","apagou as luzes.","subiu aos céus.","está no sono eterno.","deixou este mundo.","fez sua viagem final.","passou para o outro lado.","não está mais entre nós")
var  interaçãodeutudoerrado = arrayOf("A e B não querem fazer nada.","A e B não querem se falar agora.","A e B querem ficar longe um do outro.")
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
var gochisTama = mutableListOf<String>()
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
var listaDeTipoDeEmocoes = arrayOf("Normal","Feliz","Nervoso","Dormindo","Hibernando","Romântico")
var estacoes = arrayOf(estacao("   |   ".repeat(es)+"\n"+" - O - ".repeat(es)+"\n"+"   |   ".repeat(es),"Verão"),
    estacao("   |   ".repeat(es)+"\n"+"  /|\\  ".repeat(es)+"\n"+ "  \\|/  ".repeat(es),"Outono"),
    estacao("o    o ".repeat(es)+ "\n" + " o o   ".repeat(es) + "\n" +" o    o".repeat(es),"Inverno"),
    estacao(" \\ ^ / ".repeat(es)+"\n"+" < O > ".repeat(es)+"\n"+" / v \\ ".repeat(es),"Primavera"))
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
data class estacao(val intro:String, val estação: String)

data class comida (val nome : String,var qualidade: Int)


class jogoTama(tamanho : Boolean, colorido : Boolean, dinamico: Boolean) {

    var estoque = mutableListOf<comida>()
    var diaADia = Random.nextInt(1,101)
    var regi = mutableListOf<tama>()
    var famili = mutableListOf<jogoTama.gochi>()


    data class doenca(var doente: Boolean = false, var contadorDeDoenca: Int = 0,var doencaGenetica: Boolean = false,
                      var doencaContagiosa: Boolean = false, var danoDoencaConstante: Boolean = false,
                      var danoDoenca : Int = 0, var iniciarDoenca: Boolean = true)

    data class familia(var parceira: jogoTama.gochi? = null, var filho: MutableList<String>? = mutableListOf(),
                       var pais: String = "Desconhecido", var filhoID : MutableList<Int> = mutableListOf(), var paisID : MutableList<Int> = mutableListOf(),
                       var nomeParceira : String? = null, var parceiraID : Int? = null)


    data class sentimentos(val pastaEmocao: String, val emocaoString: String)


    data class caracteristicasTama(
        var vida : Int = 50, var fome : Int = 50, var felicidade : Int = 50, var sexappel : Int = 50, var vidaReal: Int = 0, var fomeReal : Int = 0,
        var felicidadeReal : Int = 0, var sexappelReal: Int = 0, var tamanhoDaFamilia : Int = 4, var porcentagemGeral : Double = 0.3,
        var ativo : Boolean = true, var chanceDeEngravidar : Int = 0, var frases: MutableList<String> = mutableListOf(), var intensidadeCustomizadaBoolean : Boolean = false,
        var intensidadeCustomizadaString: String = "", var desenho: Boolean? = false, var nome : String = "", var idade : Int = 0, var janela : janela? = null,
        var diaParaTrocaDeEmocao: Int = 0, var luto : Boolean = false, var identificador : Int = Random.nextInt(1,1001), var seed : Int = 0, var anoNasci: Int = 0, var anoMorte: Int? = null, var vivo:Boolean = true,
        var intros : MutableList<String> = mutableListOf(), var intro : String = "", var carinho : Boolean = false, var fugiu: Boolean = false, var velocidade : Long = 50, var tipoDoGochi: String = "Gochi generico", var danoDeFome: Int? = null
    )

    data class atributos(val variavel : String ,val valor : String)

    data class tama(val nome: String, val idade: Int, val frase : String, val anoMorte:Int? = 0,
                    val anoNasci: Int, val icon : String, val vivo :Boolean, var causaDaMorte: String? = "Morte morrida",
                    val parceira: String? = "não teve", val pais:String? = "Não sabe", val filho: MutableList<String>? = null,
                    val analiseDaVida : String = "Teve uma vida feliz", val fugir : Boolean )


    data class rival(var rivalNome: String? = null,var rivalidade : Boolean = false,var rivalTipoGochi: String? = null, var rivalIdentificador: Int? = null,var rivalSeed: Int? = null)


    var tamanho = tamanho;
    var colorido = colorido;
    var periodoDoAno = 0;
    var estacao = estacoes[periodoDoAno].estação;
    var evento = "Normal"
            ;
    var dinheiro = 0;
    var asciiDeApresentaçãoDaEstacao = estacoes[periodoDoAno].intro
    var mapaDeComida = false
    var rastreadorDeComida = false
    var processodinamico: Thread? = null
    fun eventospecial() {
        if (estacao == "Inverno" || estacao == "Verão") {
            var numeroaleatorio = Random.nextInt(1, 32)
            if (numeroaleatorio == 24 || numeroaleatorio == 25) {
                evento = eventos[1]
                println("Hoje é natal")
            } else if (numeroaleatorio == 31) {
                evento = eventos[2]
                println("Hoje é ano novo")
            } else {
                evento = eventos[0]
            }
        }

    }

    fun tempo() {
        if (periodoDoAno < estacoes.size) {
            estacao = estacoes[periodoDoAno].estação
            asciiDeApresentaçãoDaEstacao = estacoes[periodoDoAno].intro
            periodoDoAno++
        } else {
            periodoDoAno = 0
            estacao = estacoes[periodoDoAno].estação
            asciiDeApresentaçãoDaEstacao = estacoes[periodoDoAno].intro
        }
    }

    inner class gochi{
        var caracteristicas = caracteristicasTama()
        var doenca = doenca()
        var familia = familia()
        var rival = rival()
        var listaDeEmocao = mutableListOf<sentimentos>()
        var emocao: sentimentos? = null;
        var remedio = false
        var videojogo = false;
        var contadorDeAbstinenciadeVideojogos = 0
        var vacina = false;
        var acariciador = false;
        var bolodeprestigio = false;
        var brinquedo = false;
        var acordomorte = false;
        fun doente() {
            if (doenca.iniciarDoenca) {
                println("${caracteristicas.nome} ficou doente")
                doenca.danoDoencaConstante = Random.nextBoolean()
            }
            if (doenca.iniciarDoenca || !doenca.danoDoencaConstante) {
                if (caracteristicas.idade < 4) {
                    doenca.danoDoenca = Random.nextInt(0, 3)
                } else if (caracteristicas.idade < 14) {
                    doenca.danoDoenca = Random.nextInt(0, 6)
                } else {
                    doenca.danoDoenca = Random.nextInt(0, 11)
                }
            }
            if (doenca.doente) {
                caracteristicas.vidaReal -= doenca.danoDoenca
                doenca.iniciarDoenca = false
                if (!doenca.iniciarDoenca) {
                    var cura = Random.nextInt(101)
                    var nãofunciona = caracteristicas.idade * 2
                    if (cura > nãofunciona) {
                        println("${caracteristicas.nome} foi curado")
                        doenca.contadorDeDoenca--
                        if (doenca.contadorDeDoenca == 0) {
                            doenca.doente = false
                            doenca.iniciarDoenca = true
                        }
                    } else if (cura == doenca.danoDoenca) {
                        println("A doença de ${caracteristicas.nome} piorou")
                        doenca.danoDoenca *= 2
                        caracteristicas.vidaReal -= doenca.danoDoenca

                    } else {
                        println("${caracteristicas.nome} continua doente")

                    }
                }
            }
        }

        fun acao() {
            var palavra = if (diaADia <= caracteristicas.felicidadeReal) palaBoa[Random.nextInt(0, palaBoa.size)] else palamal[Random.nextInt(
                0,
                palamal.size
            )]
            var ato = ""
            var frase = ""
            while (true) {

                if (diaADia == 66) {
                    frase = "O(a) ${caracteristicas.nome} teve aula com o Júlio e quase chorou."
                    caracteristicas.felicidadeReal -= 7
                    break
                } else if (diaADia == 61) {
                    frase = "O(a) ${caracteristicas.nome} recebeu o Jaílson em casa."
                    if (diaADia <= caracteristicas.felicidadeReal) caracteristicas.felicidadeReal -= 7 else caracteristicas.felicidadeReal -= 3
                    break
                } else if (diaADia == 77) {
                    frase = "O(a) ${caracteristicas.nome} recebeu o Gabriel em casa."
                    if (diaADia <= caracteristicas.felicidadeReal) caracteristicas.felicidadeReal += 3 else caracteristicas.felicidadeReal -= 3
                    break
                } else if (caracteristicas.idade < 4) {
                    ato = palaInfancia[Random.nextInt(0, palaInfancia.size)]
                } else if (caracteristicas.idade < 14) {
                    ato = palaAco[Random.nextInt(0, palaAco.size)]
                } else {
                    ato = palaVelhice[Random.nextInt(0, palaVelhice.size)]
                }
                frase = "O(a) ${caracteristicas.nome} $palavra $ato"
                break
            }
            println(frase)
            caracteristicas.frases.add(frase)
        }

        fun romance() {
            var numeroaleatorio: Int
            if (emocao!!.emocaoString == listaDeTipoDeEmocoes[5]) {
                numeroaleatorio = Random.nextInt(1, (caracteristicas.seed + 1 * 1.5).toInt())
            } else {
                numeroaleatorio = Random.nextInt(1, caracteristicas.seed + 1)
            }
            if (famili.size <= caracteristicas.tamanhoDaFamilia) {
                if (familia.parceira == null) {
                    if (numeroaleatorio <= caracteristicas.sexappelReal) {
                        println("$caracteristicas.nome encontrou uma parcera(o)")
                        caracteristicas.felicidadeReal += 10
                        familia.parceira = gochi()
                        familia.nomeParceira = familia!!.parceira!!.caracteristicas.nome
                        familia.parceira!!.familia.nomeParceira = caracteristicas.nome
                        familia.parceira!!.caracteristicas.idade = Random.nextInt(5, 14)
                        familia.parceira!!.caracteristicas.ativo = false
                        familia.parceira!!.familia.parceiraID = caracteristicas.identificador
                        familia.parceiraID = familia.parceira!!.caracteristicas.identificador
                    }
                } else {
                    var realchancedeengravidar =
                        if (familia.parceira!!.caracteristicas.chanceDeEngravidar == 0 || caracteristicas.chanceDeEngravidar == 0) 0 else (familia.parceira!!.caracteristicas.chanceDeEngravidar + caracteristicas.chanceDeEngravidar) / 2
                    if (Random.nextInt(0, 101) <= realchancedeengravidar) {
                        var filho = gochi()
                        if(filho.caracteristicas.tipoDoGochi == caracteristicas.tipoDoGochi || filho.caracteristicas.tipoDoGochi == familia.parceira!!.caracteristicas.tipoDoGochi){
                            println("${caracteristicas.nome} e ${familia.parceira!!.caracteristicas.nome} tiveram um filho")
                            filho.familia.pais = "${caracteristicas.nome} e ${familia.parceira!!.caracteristicas.nome}"
                        }else{
                            println("${caracteristicas.nome} e ${familia.parceira!!.caracteristicas.nome} adotaram uma criança")
                            filho.familia.pais = "Pais adotivos: ${caracteristicas.nome} e ${familia.parceira!!.caracteristicas.nome}"
                            filho.caracteristicas.idade = Random.nextInt(0, 5)
                        }
                            familia.filho!!.add(filho.caracteristicas.nome)
                            familia.parceira!!.familia.filho!!.add(filho.caracteristicas.nome)

                        caracteristicas.felicidadeReal += 10
                            familia.parceira!!.caracteristicas.felicidadeReal += 10
                            filho.familia.paisID.add(caracteristicas.identificador)
                            filho.familia.paisID.add(familia.parceira!!.caracteristicas.identificador)
                            familia.filhoID.add(filho.caracteristicas.identificador)
                            familia.parceira!!.familia.filhoID.add(filho.caracteristicas.identificador)

                            if (familia.parceira!!.doenca.doencaGenetica || doenca.doencaGenetica) {
                                filho.doenca.doencaGenetica = Random.nextBoolean()
                            }
                    }
                }
            }
        }

        fun rivalidade() {
            var index = 0
            while (index in 0 until famili.size) {
                var x = famili.get(index)
                index++
                if (x != this) {
                    if (rival.rivalTipoGochi == null) {

                        if (caracteristicas.seed == x.caracteristicas.seed) {
                            rival.rivalTipoGochi = x.caracteristicas.tipoDoGochi
                            rival.rivalidade = true
                            rival.rivalSeed = x.caracteristicas.seed
                            rival.rivalNome = x.caracteristicas.nome
                            rival.rivalIdentificador = x.caracteristicas.identificador
                        }
                    } else if (!rival.rivalidade) {
                        if (x.caracteristicas.tipoDoGochi == rival.rivalTipoGochi) {
                            rival.rivalidade = true
                            rival.rivalSeed = x.caracteristicas.seed
                            rival.rivalNome = x.caracteristicas.nome
                            rival.rivalIdentificador = x.caracteristicas.identificador
                        } else if (x.caracteristicas.seed == rival.rivalSeed && rival.rivalTipoGochi == x.caracteristicas.tipoDoGochi && rival.rivalNome == x.caracteristicas.nome
                            && x.caracteristicas.identificador == rival.rivalIdentificador) {
                            var numeroaleatorio = Random.nextInt(0, 101)
                            var numeroataque = Random.nextInt(1, caracteristicas.seed + 1)
                            if (numeroaleatorio < numeroataque) {
                                x.caracteristicas.vidaReal -= 10
                                println("O ${caracteristicas.nome} bateu no ${x.caracteristicas.nome}")
                            } else if (numeroaleatorio == 100) {
                                famili.remove(x)
                                println("O ${caracteristicas.nome} matou o ${x.caracteristicas.nome}")

                            }
                        }
                    }
                }


            }
        }

        //var estados = arrayOf("Normal","Feliz","Nervoso","Dormindo","Hibernando","Romântico")
        fun emotroca() {
            if (emocao!!.emocaoString == listaDeTipoDeEmocoes[0]){
                caracteristicas.janela!!.mdg(emocao!!.pastaEmocao)
            } else if (emocao!!.emocaoString == listaDeTipoDeEmocoes[1]){//colocar mudar() a imagem do estado. ("Normal","Feliz","Nervoso","Dormindo","Hibernando","Romântico")
                caracteristicas.janela!!.mdg(emocao!!.emocaoString)
            }else if (emocao!!.emocaoString == listaDeTipoDeEmocoes[2]){
                caracteristicas.janela!!.mdg(emocao!!.pastaEmocao)
            } else if (emocao!!.emocaoString == listaDeTipoDeEmocoes[3]){
                caracteristicas.janela!!.mdg(emocao!!.pastaEmocao)
            }else if (emocao!!.emocaoString == listaDeTipoDeEmocoes[4]) {
                caracteristicas.janela!!.mdg(emocao!!.pastaEmocao)
        }else if (emocao!!.emocaoString == listaDeTipoDeEmocoes[5]){
                caracteristicas.janela!!.mdg(emocao!!.pastaEmocao)
    }

}
        fun emocao(){
            var personalidade = Random.nextInt(0, 101)
            if( listaDeEmocao[0].emocaoString == listaDeTipoDeEmocoes[0]){
                emocao = if (caracteristicas.seed < personalidade) listaDeEmocao[Random.nextInt(0,listaDeEmocao.size)] else listaDeEmocao[0]}
            else{
                emocao =  listaDeEmocao[Random.nextInt(0,listaDeEmocao.size)]
            }
            if(emocao!!.emocaoString == listaDeEmocao[1].emocaoString || emocao!!.emocaoString == listaDeEmocao[2].emocaoString){
                emocao = if(personalidade < caracteristicas.felicidadeReal) listaDeEmocao[1] else listaDeEmocao[2]
            }
            println(emocao)
            emotroca()
        }
        var analisedavida : String? = null
        fun fugir() {
            caracteristicas.fugiu = true
            var fugir = Random.nextInt(0, 101)
            if (fugir == 0) {
                tocador.mudar(musicasJogo[9])
                println("O ${caracteristicas.nome} fugiu para casa do Gabs. Ele acabou morrendo pela metralhadora de piadas sem graça do Gabriel porque não levantou no ônibus para uma menina")
                caracteristicas.vivo = false
                caracteristicas.anoMorte = dia
                readln()

            } else if (fugir == 66 || fugir == 61) {
                caracteristicas.vivo = false
                caracteristicas.anoMorte = dia
                causadamorte = "O(a) ${caracteristicas.nome} vendo uma careca lustrada"
                tocador.mudar(musicasJogo[Random.nextInt(9, 11)])
                println("O ${caracteristicas.nome}fugiu para casa do JaJa. Ele acabou morrendo de desgosto e suas últimas palavras foram: 'CARECA,CARECA,CARECA'")
                readln()

            } else if (fugir == 1) {
                var numeroaleatorion = Random.nextInt(0, musicasJailson.size)
                tocador.mudar(musicasJailson[numeroaleatorion])
                caracteristicas.vivo = false
                caracteristicas.anoMorte = dia
                causadamorte = "O(a) ${caracteristicas.nome} morreu Descobrindo a verdade"
                caracteristicas.janela!!.mdg("./Assets/finaJailson/1/1.png")
                println(
                    "O ${caracteristicas.nome} fugiu para casa do JaJa. Depois de anos juntos, a máscara da mentira foi quebrada e sua face foi beijada pela verdade, nos últimos minutos de vida, ele disse: 'O Jaílson é muito melhor que o Gabriel e ele é o melhor professor do Pr... de todos os Senacs':" +
                            "\nAqui é o JaJa, o dono da caneta, Chego pesado, te deixo na treta. Sua lista da pureza não é nada, Só fachada, tua moral tá quebrada.\n" +
                            "\n" +
                            "Já era, acabou, o JaJa te detonou, Com cada verso, o palco inteiro ecoou. Gabs, meu chapa, tá na hora de aceitar, No código e no flow, você não vai me alcançar.\n" +
                            "\n" +
                            "Eu sou o Jailson, o mestre da codificação, Te deixo perdido, sem direção. Enquanto eu brilho, você só apaga, Na batalha, meu nome é quem se propaga."
                )
                analisedavida = "O(a) ${caracteristicas.nome} teve uma vida de revelações"
                readln()

            } else if (fugir <= 50) {
                tocador.mudar(musicasJogo[8])
                println("O ${caracteristicas.nome} fugiu para casa do Gabs. Ele teve uma vida feliz e saudável")
                readln()

                analisedavida = "Teve uma vida feliz"
            } else if (fugir == 100) {
                var numeroaleatorion = Random.nextInt(0, musicasGabriel.size)
                tocador.mudar(musicasGabriel[numeroaleatorion])
                caracteristicas.vivo = false
                caracteristicas.anoMorte = dia
                causadamorte = "O(a) ${caracteristicas.nome} morreu por ser feliz demais"

                causadamorte = "Ser feliz demais"
                println(
                    "O ${caracteristicas.nome} fugiu para casa do Gabs. Eles viveram felizer para sempre e nos seus últimos momentos cantaram em uma só voz: " +
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
                            "Tropa do Gabs, ninguém pode deter. "
                )
                analisedavida = "O(a) ${caracteristicas.nome} teve uma vida boa demais feliz"

                readln()
            } else {
                tocador.mudar(musicasJogo[6])
                println("O ${caracteristicas.nome} fugiu para casa do JaJa. Ele viveu uma vida triste e miserável")
                readln()
                analisedavida = "O(a) ${caracteristicas.nome} teve uma vida triste"
            }
            if (analisedavida == null) {
                if (diafeliz > diatriste) {
                    analisedavida =
                        if (diafeliz > diatriste) "O(a) ${caracteristicas.nome} teve uma vida feliz" else "O(a) ${caracteristicas.nome} teve uma vida triste"
                }
            }
            println(analisedavida)
            famili.remove(this)
            var frase = caracteristicas.frases[Random.nextInt(0, caracteristicas.frases.size)]
            regi.add(
                tama(
                    caracteristicas.nome,
                    caracteristicas.idade,
                    frase,
                    caracteristicas.anoMorte,
                    caracteristicas.anoNasci,
                    caracteristicas.janela!!.icon(),
                    caracteristicas.vivo,
                    causadamorte,
                    familia.nomeParceira,
                    familia.pais,
                    familia.filho,
                    analisedavida!!,
                    caracteristicas.fugiu
                )
            )
            caracteristicas.janela!!.fechar()
            var index = 0
            while (index < famili.size) {
                var gochiTama = famili.get(index)
                gochiTama.caracteristicas.luto = true
                listaDeEmocao.forEach { sentimentos ->
                    if (sentimentos.emocaoString == listaDeTipoDeEmocoes[2]) {
                        emocao = sentimentos
                        emotroca()
                    }
                }
            }
        }
        init {




            var caminho = gochisTama[Random.nextInt(0,gochisTama.size - bloqueio)]
            var caminhotamas = File(caminho).listFiles().filter{ it.isDirectory }

            caminhotamas.forEach { pasta ->
                var chamado: String? = null
                for (x in pasta.name) {
                    if (x.isDigit()) {
                        if(chamado == null) chamado = ""
                        chamado += x

                    } else {
                        break
                    }

                }
                var realChamado = pasta.path
                if(pasta.name.uppercase() == "INTRO"){
                    caracteristicas.intro = realChamado
                }else if (chamado != null) {
                    var chamadoNumero = chamado.toString().toInt()

                    if (chamadoNumero == 1) {
                        listaDeEmocao.add(sentimentos(realChamado, listaDeTipoDeEmocoes[0]))
                    } else if (chamadoNumero == 2) {
                        listaDeEmocao.add(sentimentos(realChamado, listaDeTipoDeEmocoes[1]))
                    } else if (chamadoNumero == 3) {
                        listaDeEmocao.add(sentimentos(realChamado, listaDeTipoDeEmocoes[2]))
                    } else if (chamadoNumero == 4) {
                        listaDeEmocao.add(sentimentos(realChamado, listaDeTipoDeEmocoes[3]))
                    } else if (chamadoNumero == 5) {
                        listaDeEmocao.add(sentimentos(realChamado, listaDeTipoDeEmocoes[4]))
                    } else if (chamadoNumero == 6) {
                        listaDeEmocao.add(sentimentos(realChamado, listaDeTipoDeEmocoes[5]))
                    }
                }
                if (pasta.name == "config.txt") {
                    var variaveis = mutableListOf<atributos>()

                    pasta.forEachLine { frase ->
                        var chamada: String? = null
                        var numero: String? = null
                        var palavra: String? = null
                        var chamadobol = true
                        var numeroBoolean = false
                        var palavraBoolean = false
                        var parentese = false
                        for (x in frase) {
                            if (x == '"' || parentese){

                                if ( palavra?.lastOrNull().toString() != "\\" && x == '"' ){
                                    if (parentese){
                                        parentese = false
                                    }else{
                                        parentese = true
                                    }
                                }else{
                                    if(x == '"'){
                                       palavra =  palavra?.dropLast(1)
                                    }
                                    if (palavra == null) {
                                        palavra = ""
                                    }

                                    palavra+= x

                                    palavraBoolean = true
                                }
                            }else if (x == '=' || chamadobol == false) {
                                chamadobol = false
                                if (x.isDigit() && !palavraBoolean) {
                                    if (numero == null) {
                                        numero = ""
                                    }

                                    numero += x
                                    numeroBoolean = true

                                } else if (x.isLetter() && !numeroBoolean) {
                                    if (palavra == null) {
                                        palavra = ""
                                    }
                                    palavra += x
                                    palavraBoolean = true

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
                            if (palavraBoolean) {
                                valor = palavra!!
                            } else {
                                valor = numero!!
                            }}
                        if(chamada != null || valor != null){
                            variaveis.add(atributos(chamada!!, valor!!))}
                    }
                    for (x in variaveis) {
                        if (x.variavel.uppercase().replace(" ", "") == "VIDA") {
                            caracteristicas.vida = x.valor.toInt()
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "FOME") {

                            var realnumber = x.valor.toInt()
                            caracteristicas.fome = realnumber!!
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "FELICIDADE") {

                            var realnumber = x.valor.toInt()
                            caracteristicas.felicidade = realnumber!!
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "SEXAPPEL") {

                            var realnumber = x.valor.toInt()
                            caracteristicas.sexappel = realnumber!!
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "GOCHI") {

                            caracteristicas.tipoDoGochi = x.valor
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "RIVAL") {

                            rival.rivalTipoGochi = x.valor
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "INTENSIDADE") {
                            caracteristicas.intensidadeCustomizadaBoolean = true
                            caracteristicas.intensidadeCustomizadaString = x.valor
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "DESENHO") {

                            caracteristicas.desenho = x.valor.toBoolean()
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "PORCENTAGEMGERAL") {

                            caracteristicas.porcentagemGeral = x.valor.toDouble()/100
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "DANO") {

                            dano = x.valor.toDouble()
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "MULTIPLICADORDANO") {
                            valormultidano = x.valor.toDouble()
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "DANOFELICIDADE") {

                            danoFelicidade = x.valor.toDouble()
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "MULTIPLICADORDANOFELICIDADE") {

                            valormultidanofelicidade = x.valor.toDouble()
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "VELOCIDADE") {

                            caracteristicas.velocidade = x.valor.toLong()
                        } else if (x.variavel!!.uppercase().replace(" ", "") == "TAMANHOFAMILIA") {

                            caracteristicas.tamanhoDaFamilia = x.valor.toInt()
                        }else if (x.variavel!!.uppercase().replace(" ", "") == "DANOFOME") {

                            caracteristicas.danoDeFome = x.valor.toInt()
                        }




                    }

                }


            }
            var fileIntro = File(caracteristicas.intro).listFiles().filter { it.extension == "wav" }
            fileIntro.forEach(){
                    audio -> caracteristicas.intros.add(audio.path)
            }
            var audioReal = caracteristicas.intros[Random.nextInt(0,caracteristicas.intros.size)]

            caracteristicas.anoNasci = dia
            caracteristicas.idade = 0
            caracteristicas.seed = Random.nextInt(1,diaADia+1)

            caracteristicas.vida -= ((caracteristicas.vida * caracteristicas.porcentagemGeral).toFloat() * (caracteristicas.seed / 100).toFloat()).toInt()
            caracteristicas.fome -= ((caracteristicas.fome * caracteristicas.porcentagemGeral).toFloat() * (caracteristicas.seed / 100).toFloat()).toInt()
            caracteristicas.felicidade -= ((caracteristicas.felicidade * caracteristicas.porcentagemGeral).toFloat() * (caracteristicas.seed / 100).toFloat()).toInt()
            caracteristicas.sexappel -= ((caracteristicas.sexappel * caracteristicas.porcentagemGeral).toFloat() * (caracteristicas.seed / 100).toFloat()).toInt()
            caracteristicas.vidaReal = caracteristicas.vida
            caracteristicas.fomeReal = caracteristicas.fome
            caracteristicas.felicidadeReal = caracteristicas.felicidade
            caracteristicas.sexappelReal = caracteristicas.sexappel

if(diaADia%10 == 0){
    doenca.doencaGenetica = true
}

            caracteristicas.diaParaTrocaDeEmocao = (caracteristicas.seed/10)
            if (caracteristicas.diaParaTrocaDeEmocao == 0){
                caracteristicas.diaParaTrocaDeEmocao = caracteristicas.diaParaTrocaDeEmocao!! + 1
            }else if(caracteristicas.diaParaTrocaDeEmocao!! > 10){
                caracteristicas.diaParaTrocaDeEmocao = caracteristicas.diaParaTrocaDeEmocao!! - 1
            }
            caracteristicas.chanceDeEngravidar = if (caracteristicas.seed > 20) 20 else caracteristicas.seed
            var numeroaleatorio = Random.nextInt(1,101)
            tamanho = if (numeroaleatorio == 100) !tamanho else tamanho
            numeroaleatorio = Random.nextInt(1,101)
            colorido = if(numeroaleatorio == 100) !colorido else colorido
            var intensidadeAleatoria = if (caracteristicas.intensidadeCustomizadaBoolean) 11 else Random.nextInt(1,11)
            emocao = listaDeEmocao[0]
            caracteristicas.janela = janela(tamanho,colorido,caracteristicas.desenho!!,emocao!!.pastaEmocao,"",intensidadeAleatoria,caracteristicas.intensidadeCustomizadaString!!, caracteristicas.velocidade)
            println("Escreva o nome do TamaGochi")
            caracteristicas.nome = readln()
            caracteristicas.janela!!.title = "Nome: ${caracteristicas.nome} | Idade: ${caracteristicas.idade} | Vida: ${caracteristicas.vidaReal} | Fome: ${caracteristicas.fomeReal}"
            caracteristicas.janela!!.terminando()
            tocador.mudar(audioReal)
            famili.add(this)


        }

        fun comer(qualidade: Int){
            comer = 0
            var numeroAleatorio = Random.nextInt(1,caracteristicas.seed+1)
            caracteristicas.felicidadeReal += qualidade*(diaADia/100) - numeroAleatorio
            if(qualidade > 30){
                caracteristicas.felicidadeReal += qualidade/10
            }

        }
        fun danoFome(){
            var danoFome = 0
            if(caracteristicas.danoDeFome == null){
            danoFome = Random.nextInt(0,caracteristicas.seed+1/10)}else {danoFome = caracteristicas.danoDeFome!!}
            if(caracteristicas.carinho){
                danoFome = (danoFome * 1.5).toInt()
            }
            caracteristicas.fomeReal -= danoFome
        }
        fun brincadeira(){
            caracteristicas.carinho = true
            var numeroAleatorioBrincadeira = 0
            var afinidade = (caracteristicas.idade/100) + 1
            if (emocao!!.emocaoString == listaDeTipoDeEmocoes[1]){
                numeroAleatorioBrincadeira = Random.nextInt(1, ((caracteristicas.seed+1)*1.5).toInt())
            }else if (emocao!!.emocaoString == listaDeTipoDeEmocoes[2]){
                numeroAleatorioBrincadeira = Random.nextInt(1, ((caracteristicas.seed+1)*0.5).toInt())

            }else{
                numeroAleatorioBrincadeira = Random.nextInt(1, caracteristicas.seed+1)}
            caracteristicas.felicidadeReal += numeroAleatorioBrincadeira * afinidade
            if(videojogo){
                caracteristicas.felicidadeReal += 10 - contadorDeAbstinenciadeVideojogos
            }
        }
        var causadamorte : String? = null
        fun morreu(){
            caracteristicas.vivo = false
            famili.remove(this)
            var momentodamorte = palaAcoMorte[Random.nextInt(0,palaAcoMorte.size-1)]
            if (causadamorte == null){
                causadamorte = "O(a) ${caracteristicas.nome} morreu $momentodamorte"
                println(causadamorte)

            }
            analisedavida = if(diafeliz > diatriste) "O(a) ${caracteristicas.nome} teve uma vida feliz" else "O(a) ${caracteristicas.nome} teve uma vida triste"
            var frase = caracteristicas.frases[Random.nextInt(0,caracteristicas.frases.size)]
            println(analisedavida)
            caracteristicas.anoMorte = dia
            regi.add(tama(caracteristicas.nome, caracteristicas.idade,frase,caracteristicas.anoMorte,caracteristicas.anoNasci,caracteristicas.janela!!.icon(),caracteristicas.vivo,causadamorte,familia.nomeParceira,familia.pais,familia.filho,analisedavida!!,caracteristicas.fugiu))
            caracteristicas.janela!!.fechar()
            var index = 0
            while(index < famili.size){
                var tama = famili.get(index)
                tama.caracteristicas.luto = true
listaDeEmocao.forEach {
    if(it.emocaoString == listaDeTipoDeEmocoes[2]){
        emocao = it
        emotroca()
}
                }
            }
        }
        fun acaofamilia(){
            var index = 0
            while (index < famili.size){
                var numeroaleatorio = Random.nextInt(1,101)
                var tama = famili.get(index)
                if(!tama.equals(this) && numeroaleatorio in 1..10){
                    if(tama.caracteristicas.identificador == familia.parceiraID){
                        if(diaADia < caracteristicas.felicidadeReal && diaADia < tama.caracteristicas.felicidadeReal){
                            println(interaçãopositivaparceiraparceiro[Random.nextInt(0,interaçãopositivaparceiraparceiro.size)].replace("B",caracteristicas.nome.lowercase()).replace("A",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(),caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(),tama.caracteristicas.nome))
                            caracteristicas.felicidadeReal+= 10
                            tama.caracteristicas.felicidadeReal += 10
                        }else if (diaADia < caracteristicas.felicidadeReal || diaADia < tama.caracteristicas.felicidadeReal){
                            println(interaçãonegativaparceiroparceira[Random.nextInt(0,interaçãonegativaparceiroparceira.size)].replace("B",caracteristicas.nome.lowercase()).replace("A",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(),caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(),tama.caracteristicas.nome))
                        tama.caracteristicas.felicidadeReal -= 5
                            caracteristicas.felicidadeReal -= 5
                        }else{
                            println(interaçãodeutudoerrado[Random.nextInt(0,interaçãodeutudoerrado.size)].replace("A",caracteristicas.nome.lowercase()).replace("B",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(), caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(), tama.caracteristicas.nome))
                        tama.caracteristicas.felicidadeReal -= 10
                            caracteristicas.felicidadeReal -= 10
                        }
                    }else if (tama.caracteristicas.identificador in familia.paisID){
                        if(diaADia < caracteristicas.felicidadeReal && diaADia < tama.caracteristicas.felicidadeReal){
                            println(interaçãopositivapais[Random.nextInt(0,interaçãopositivapais.size)].replace("B",caracteristicas.nome.lowercase()).replace("A",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(),caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(),tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal += 10
                            caracteristicas.felicidadeReal += 10
                        }else if (diaADia < caracteristicas.felicidadeReal){
                            println(interaçãonegativapaispais[Random.nextInt(0,interaçãonegativapaispais.size)].replace("B",caracteristicas.nome.lowercase()).replace("A",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(),caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(),tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal -= 5
                            caracteristicas.felicidadeReal -= 3
                        }else if (diaADia < tama.caracteristicas.felicidadeReal){
                            println(interaçãonegativafilhospais[Random.nextInt(0,interaçãonegativafilhospais.size)].replace("B",caracteristicas.nome.lowercase()).replace("A",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(),caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(),tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal -= 3
                            caracteristicas.felicidadeReal -= 5
                        } else{
                            println(interaçãodeutudoerrado[Random.nextInt(0,interaçãodeutudoerrado.size)].replace("A",caracteristicas.nome.lowercase()).replace("B",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(), caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(), tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal -= 10
                            caracteristicas.felicidadeReal -= 10
                        }
                    }else if (tama.caracteristicas.identificador in familia.filhoID){
                        if(diaADia < caracteristicas.felicidadeReal && diaADia < tama.caracteristicas.felicidadeReal){
                            println(interaçãopositivafilhos[Random.nextInt(0,interaçãopositivafilhos.size)].replace("A",caracteristicas.nome.lowercase()).replace("B",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(), caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(), tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal += 10
                            caracteristicas.felicidadeReal += 10
                        }else if (diaADia < caracteristicas.felicidadeReal){
                            println(interaçãonegativafilhosfilhos[Random.nextInt(0,interaçãonegativafilhosfilhos.size)].replace("A",caracteristicas.nome.lowercase()).replace("B",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(), caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(), tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal -= 5
                            caracteristicas.felicidadeReal -= 3
                        }else if (diaADia < tama.caracteristicas.felicidadeReal){
                            println(interaçãonegativapaisfilhos[Random.nextInt(0,interaçãonegativapaisfilhos.size)].replace("A",caracteristicas.nome.lowercase()).replace("B",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(), caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(), tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal -= 3
                            caracteristicas.felicidadeReal -= 5
                        } else{
                            println(interaçãodeutudoerrado[Random.nextInt(0,interaçãodeutudoerrado.size)].replace("A",caracteristicas.nome.lowercase()).replace("B",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(), caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(), tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal -= 10
                            caracteristicas.felicidadeReal -= 10
                        }
                    }else if (tama.familia.paisID == familia.paisID){
                        if(diaADia < caracteristicas.felicidadeReal && diaADia < tama.caracteristicas.felicidadeReal){
                            println(interaçãopositivairmãoirmã[Random.nextInt(0,interaçãopositivairmãoirmã.size)].replace("B",caracteristicas.nome.lowercase()).replace("A",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(),caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(),tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal += 10
                            caracteristicas.felicidadeReal += 10
                        }else if (diaADia < caracteristicas.felicidadeReal || diaADia < tama.caracteristicas.felicidadeReal){
                            println(interaçãonegativairmãoirmã[Random.nextInt(0,interaçãonegativairmãoirmã.size)].replace("B",caracteristicas.nome.lowercase()).replace("A",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(),caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(),tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal -= 5
                            caracteristicas.felicidadeReal -= 5
                        }else{
                            println(interaçãodeutudoerrado[Random.nextInt(0,interaçãodeutudoerrado.size)].replace("A",caracteristicas.nome.lowercase()).replace("B",tama.caracteristicas.nome.lowercase()).replace(caracteristicas.nome.lowercase(), caracteristicas.nome).replace(tama.caracteristicas.nome.lowercase(), tama.caracteristicas.nome))
                            tama.caracteristicas.felicidadeReal -= 10
                            caracteristicas.felicidadeReal -= 10
                        }
                    }
                }
                index++
            }
        }
        var valormultidano = 1.0
        var dano = 10 - (10*0.3)*(caracteristicas.seed/100)
        fun dano(){
            var multiplicadorDano = valormultidano
            if (estacao == "Inverno") {
                multiplicadorDano = 2.0

            }
            var danoreal = dano * multiplicadorDano
            if(!acordomorte){
                caracteristicas.vidaReal-= (danoreal - (danoreal*(((caracteristicas.fomeReal+caracteristicas.felicidadeReal)/2)/100))).toInt()}
        }
        var danoFelicidade = 10 - (10*0.3)*(caracteristicas.seed/100)
var valormultidanofelicidade = 1.0
        fun danofelicidade(){
            var multiplicadorDanodefelicidade = valormultidanofelicidade

            var afinidade = (caracteristicas.idade/100) + 1
            if (estacao == "Inverno") {
                multiplicadorDanodefelicidade = 2.0

            }
            if(caracteristicas.idade < 4){
                danoFelicidade /= 2
                multiplicadorDanodefelicidade -= 0.5
            }else if (caracteristicas.idade >= 14){
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
                if(!caracteristicas.carinho){
                    if(caracteristicas.idade < 14){
                        caracteristicas.felicidadeReal -= ((afinidade * danoreal*0.5 + (danoreal - danoreal*(caracteristicas.fomeReal/100)))/2).toInt()}else{
                            caracteristicas.felicidadeReal -= ((afinidade * danoreal*0.5 + (danoreal - danoreal*(caracteristicas.fomeReal/100)))/3).toInt()}
                }else{
                    if(caracteristicas.idade < 14){
                        caracteristicas.felicidadeReal-= ((danoreal*0.5 + (danoreal - danoreal*(caracteristicas.fomeReal/100)))/2).toInt()}else{
                            caracteristicas.felicidadeReal-= ((danoreal*0.5 + (danoreal - danoreal*(caracteristicas.fomeReal/100)))/1.5).toInt()
                    }
                }

            }else{
            if(!caracteristicas.carinho){
                    caracteristicas.felicidadeReal-= (afinidade * danoreal*0.5 + (danoreal - danoreal*(caracteristicas.fomeReal/100))).toInt()
            }else{
                    caracteristicas.felicidadeReal-= (danoreal*0.5 + (danoreal - danoreal*(caracteristicas.fomeReal/100))).toInt()
            }}

        }
        var comer = 0
        var diafeliz = 0
        var diatriste = 0
        fun check(){
            var multiplasdoenças = Random.nextInt(0,(caracteristicas.idade+1)*2)
            if(!caracteristicas.vivo){
                morreu()
                return
            }
if(vacina){
    doenca.doente = false
    doenca.contadorDeDoenca = 0
    doenca.doencaContagiosa = false
}
            if (!vacina) {
                var saude = Random.nextInt(1, 101)
                if (caracteristicas.idade < 4 && saude in 1..10 ) {
                    doenca.doente = true
                    doenca.contadorDeDoenca++
                } else if (caracteristicas.idade < 14 && saude in 1..20) {
                    doenca.doente = true
                    doenca.contadorDeDoenca++

                } else if (caracteristicas.idade >= 14 && saude in 1..40) {
                    doenca.doente = true
                    doenca.contadorDeDoenca++
                }
            }
            if(caracteristicas.seed in 1..multiplasdoenças && doenca.doente){
                doenca.contadorDeDoenca++
                if(!doenca.doencaContagiosa && Random.nextInt(1,11) ==  1){
                    doenca.doencaContagiosa = true
                }
            }
            if(doenca.doente){
             for (doenca in 1..doenca.contadorDeDoenca){
                 doente()
             }
                if(!doenca.iniciarDoenca && Random.nextInt(1,11) ==  1){
                    doenca.doencaContagiosa = true
                }
            }
            if(doenca.doencaContagiosa && famili.size > 1){
                var index = 0
                while (index < famili.size){
                    var ficardoente = Random.nextInt(1,11)
                    if(!famili.get(index).equals(this)&& ficardoente in 1..3){

                            famili.get(index).doenca.contadorDeDoenca++
                            famili.get(index).doenca.doente = true
                            famili.get(index).doenca.doencaContagiosa = true
                    }
                }
            }

            if (dia % caracteristicas.diaParaTrocaDeEmocao!! == 0) {
                emocao()
            }

            if(caracteristicas.idade > 4){
                if (caracteristicas.felicidadeReal <= 0){
                    fugir()
                    return
                }

                rivalidade()

                if(caracteristicas.ativo){
                    romance()
                }

            }

            var ataquecardiaco = Random.nextBoolean()
            if (ataquecardiaco && caracteristicas.idade > 14){
                println("Morreu de ataque cardiaco.")
                morreu()
                return
            }
            if(acordomorte){
                var index = 0
                while (index < famili.size){
                    if(!famili.get(index).equals(this)){
                    var morto = Random.nextBoolean()
                    if(morto){
                        println("O(a) ${famili.get(index).caracteristicas.nome} morreu de maneira estranha.")
                        famili.get(index).morreu()
                    }
                }}
            }
if(caracteristicas.luto){
    danofelicidade()
    caracteristicas.luto = false
}
            if(famili.size > 1){
                acaofamilia()
            }
            var sorte = Random.nextInt(1,101)
            if(sorte in 1..10){
                println("O(a) gochi ${caracteristicas.nome} encontrou dinheiro.")
                dinheiro++
            }else if(sorte == 1 || sorte == 100){
                println("O(a) gochi ${caracteristicas.nome} encontrou muito dinheiro.")
                dinheiro += 3
            }
            if(doenca.doencaGenetica){
                caracteristicas.vidaReal -= Random.nextInt(0,6)
            }
            if (caracteristicas.vidaReal <= 0){
                morreu()
                return
            }else if (caracteristicas.vidaReal > caracteristicas.vida){
                caracteristicas.vidaReal = caracteristicas.vida
            }
            if (caracteristicas.felicidadeReal > caracteristicas.felicidade){
                caracteristicas.felicidadeReal = caracteristicas.felicidade
            }
            if (caracteristicas.fomeReal == 0){
                comer ++
            }
            if (comer == 3){
                morreu()
                return
            }
            if (caracteristicas.fomeReal > caracteristicas.fome) {
                caracteristicas.fomeReal = caracteristicas.fome
            }
            if (caracteristicas.felicidadeReal > 50){
                diafeliz++
            }else{
                diatriste++
            }
            acao()
            if(diaADia == caracteristicas.seed){
                println("Hoje é o aniversário do(a) ${caracteristicas.nome}")
                caracteristicas.felicidadeReal += 10
listaDeEmocao.forEach {
    if(it.emocaoString == listaDeTipoDeEmocoes[1]){
        emocao = listaDeEmocao[1]
        emotroca()
}
                }
            }
            caracteristicas.idade++
            caracteristicas.janela!!.mudarestiloexterno()

            caracteristicas.janela!!.title = "Nome: ${caracteristicas.nome} | Idade: ${caracteristicas.idade} | Vida: ${caracteristicas.vidaReal} | Fome: ${caracteristicas.fomeReal}"
        }
        fun passardia(){
            if(emocao!!.emocaoString != listaDeTipoDeEmocoes[3] && emocao!!.emocaoString != listaDeTipoDeEmocoes [4]){
            if(remedio){
                doenca.doente = false
                remedio = false
            }
            if(vacina && caracteristicas.idade >= 14){
                vacina = false
            }
            if(acariciador && !caracteristicas.carinho){
                brincadeira()
            }
            if(bolodeprestigio){
                if(caracteristicas.idade >= 14){
                    caracteristicas.vidaReal += caracteristicas.vida/2
                    caracteristicas.fomeReal += caracteristicas.fome/2
                    caracteristicas.felicidadeReal += caracteristicas.felicidade/2
                }else{
                    caracteristicas.vidaReal = caracteristicas.vida
                    caracteristicas.fomeReal = caracteristicas.fome
                    caracteristicas.felicidadeReal = caracteristicas.felicidade
                }
                bolodeprestigio = false
            }
            danoFome()
            danofelicidade()
            dano()
            check()
        }else if( emocao!!.emocaoString == listaDeTipoDeEmocoes [3]){
              dormindo()
            }else if(emocao!!.emocaoString == listaDeTipoDeEmocoes[4]){
hibernando()
            }
        }
var conhibernando = 1

        fun hibernando(){

            danoFome()
            caracteristicas.vidaReal += Random.nextInt(1,11)

            if(caracteristicas.vidaReal > caracteristicas.vida){
                caracteristicas.vidaReal = caracteristicas.vida
            }
            if(caracteristicas.vidaReal <=0){
                morreu()
                return
            }
            caracteristicas.idade++
            for (x in 1..conhibernando){
                var numeroaleatorio = Random.nextInt(1,101)
                if(numeroaleatorio in 1..10){
                    emocao()
                    conhibernando = 0
                }
            }

        }

        fun dormindo(){
            danoFome()
            dano()
            if(caracteristicas.fomeReal < 0){
                caracteristicas.fomeReal = 0
            }
            if(caracteristicas.vidaReal <=0){
                morreu()
            }
            caracteristicas.idade++
            emocao()
        }

    }

    var ocupado = false
    var passadia = false
    var g : gochi? = null
    var acao = false
    fun darComida(){
        ocupado = true
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
                ocupado = true
                var r = readln().toIntOrNull()
                ocupado = false
                if(passadia){
                    passaardia()

                    passadia = false
                    return
                }
                while(r == null || r-1 > estoque.size -1 || r - 1 < -1){
                    println("Escreva um número")
                    ocupado = true
                    r = readln().toIntOrNull()
                    ocupado = false
                    if(passadia){
                        passaardia()

                        passadia = false
                        return
                    }
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
                println("${famili[0].caracteristicas.nome} comeu ${comidatama!!.nome}")
                tamagochi = famili[0]
            }else {
                println("Qual tama você quer alimentar")
                famili.forEachIndexed { index, gochi ->
                    println("${index + 1} - ${gochi.caracteristicas.nome}")

                }
                println("0 - Sair")
                println("Escolha um tamagochi")
                ocupado = true
                var r = readln().toIntOrNull()
                ocupado = false
                if(passadia){
                    passaardia()

                    passadia = false
                    return
                }
                while (r == null || r - 1 > famili.size - 1 || r - 1 < -1) {
                    println("Escreva um número")
                    ocupado = true
                    r = readln().toIntOrNull()
                    ocupado = false
                    if(passadia){
                        passaardia()

                        passadia = false
                        return
                    }
                }
                if (r == 0) {
                    println("Sair")
                    return
                } else {
                    if(famili.get(r.toInt() - 1).emocao!!.emocaoString != listaDeTipoDeEmocoes[3] && famili.get(r.toInt() - 1).emocao!!.emocaoString != listaDeTipoDeEmocoes[4]){
                    println("Comida: ${famili[r - 1]}")
                    tamagochi = famili[r - 1]}else{
                        println(famili.get(r.toInt() -1).caracteristicas.nome + " está dormindo")
                        return
                    }

                }
            }
                tamagochi.comer(comidatama!!.qualidade)
            estoque.remove(comidatama)

        }else{
            println("Não tem nada no estoque")
        }
    }
var dinamico = dinamico
    fun procurarComida(){
        ocupado = true
        if(!dinamico){
        acao = true}
        var multiplicador = 0.5
        if (estacao == "Verão"){
            multiplicador = 1.0
        }else if (estacao =="Inverno"){
            multiplicador = 0.5
        }else if (estacao =="Outono"){
            multiplicador = 0.8
        }else if (estacao =="Primavera"){
            multiplicador =2.0
        }
        var numeroaleatorio = diaADia
        var numeroAletorioParaoMapa = Random.nextInt(10,21)
        if(rastreadorDeComida){
            numeroaleatorio = Random.nextInt(79,101)
        }
        if(mapaDeComida && diaADia + numeroAletorioParaoMapa <=100){
            numeroaleatorio +=20
        }
        var indice = comidas.size -2
        var comida : comida
        if (numeroaleatorio in 1..40){
            comida = comidas[Random.nextInt(0,indice/4)].copy()
        }else if (numeroaleatorio == 100){
            tocador.mudar(musicasJogo[7])
            comida = comidas[Random.nextInt(indice, comidas.size)].copy()
        }else if (numeroaleatorio in 41..80){
            comida = comidas[Random.nextInt(2,indice/2)].copy()
        }else{
            comida = comidas[Random.nextInt(4,indice)].copy()

        }
        comida.qualidade = (comida.qualidade * multiplicador).toInt()
        println("Você achou ${comida.nome} e guardou no estoque")
        estoque.add(comida)

    }
    var caminhotamas = File("./Assets/tamaGochi").listFiles()
    var queroSair = false
    init {
        caminhotamas.forEach { pasta ->
            gochisTama.add(pasta.path)
        }
        pastamusicaGabriel.forEach { arquivo ->
            musicasGabriel.add(arquivo.path)
        }
        pastamusicaJailson.forEach {arquivo ->
            musicasJailson.add(arquivo.path)
        }
        g = gochi()
if(dinamico){
processodinamico =Thread{
    while(famili.size > 0){

            Thread.sleep(15000)
        while (ocupado){
            Thread.sleep(5)
        }
        if(!ocupado){
        passadia = true}
        }}
}
        while (dia == 0 || famili.size > 0 && !queroSair){
            if(dinamico){
                if(!processodinamico!!.isAlive){
                    processodinamico!!.start()
                }
                menuDinamico()
            }else{
            menu()}
            ocupado = false

        }
       despedida()

        println("Você conseguiu Chegar à $dia")
    }
    fun despedida(){
        regi.forEach {
                tama ->
            println(tama.icon)
            print("Nome: ${tama.nome}  Idade: ${tama.idade}  Ano de Nacimento: ${tama.anoNasci} ")
            if(tama.anoMorte != null){
                print("Ano da morte: ${tama.anoMorte}")
            }else{
                print("Ano da morte: ")
            }
            if(tama.fugir){
                print(" Status: fugiu.")
            }else if(tama.fugir && !tama.vivo){
                print(" Status: fugiu e ${frasemortes[Random.nextInt(0,frasemortes.size)]}")
            }else if(!tama.vivo){
                print(" Status: ${frasemortes[Random.nextInt(0,frasemortes.size)]}")
            }else{
                print(" Status: está vivo.")
            }
            if(tama.pais != null){
                print("\nPais: ${tama.pais}")
            }else{
                print("\nPais: Desconhecidos")
            }
            if(tama.parceira != null){
                print(" Parceira: ${tama.parceira}")
            }
            if(tama.filho!!.size > 0){
                print(" filho(s):")
                for (x in tama.filho!!){
                    if(tama.filho!!.indexOf(x) != tama.filho!!.size -1){
                        print(" $x,")}else{
                        print(" $x.")
                    }
                }
            }
            if(!tama.vivo){
                print("\nCausa da morte: ${tama.causaDaMorte}")
            }
            println("\nMomento marcante: ${tama.frase}")
            println(tama.analiseDaVida)

            var r = readln()
            if(r == "Sair" || r == "S" || r == "N"){
                return
            }


        }
    }
    fun menu() {
        println(asciiDeApresentaçãoDaEstacao)
        tocador.mudar(musicasJogo[periodoDoAno+2])
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
                "7" -> {
                    println( g!!.caracteristicas.janela!!.icon())}
                "6" -> {passaardia(); tocador.mudar(musicasJogo[1])}
                "5" -> {loja(); tocador.mudar(musicasJogo[1])}
                "4" -> {darComida();tocador.mudar(musicasJogo[1])}
                "3" -> {brincar(); tocador.mudar(musicasJogo[1])}
                "2"-> {estoque(); tocador.mudar(musicasJogo[1])}
                "1" -> {procurarComida(); tocador.mudar(musicasJogo[1])}
                "0" -> {queroSair = true; tocador.mudar(musicasJogo[1])}
            }}else{
            println("1"+" ".repeat(35)+"Estoque")
            println("2"+" ".repeat(35)+"Dar comida")
            println("3"+" ".repeat(35)+"Loja")
            println("4"+" ".repeat(35)+"Passar o dia")
            println("0"+" ".repeat(35)+"Sair")
            var resp = readln()

            when (resp) {
                "1" -> {estoque(); tocador.mudar(musicasJogo[1])}
                "2" -> {darComida(); tocador.mudar(musicasJogo[1])}
                "3"-> {loja();tocador.mudar(musicasJogo[1])}
                "4" -> {passaardia(); tocador.mudar(musicasJogo[1])}
                "0" -> {queroSair = true; tocador.mudar(musicasJogo[1])}

            }
        }}
    fun menuDinamico() {
        if(passadia){
            passaardia()
            passadia = false
            return
        }
        println(asciiDeApresentaçãoDaEstacao)
        tocador.mudar(musicasJogo[periodoDoAno+2])
        println("-".repeat(40)+"Menu"+"-".repeat(40))
            println("1"+" ".repeat(35)+"Procurar comida")
            println("2"+" ".repeat(35)+"Estoque")
            println("3"+" ".repeat(35)+"Brincadeira")
            println("4"+" ".repeat(35)+"Dar comida")
            println("5"+" ".repeat(35)+"Loja")
            println("0"+" ".repeat(35)+"Sair")

ocupado = true
            var resp = readln()
ocupado = false
            if(passadia){
                passaardia()

                passadia = false
                return
            }
            when (resp) {
                "5" -> {loja(); tocador.mudar(musicasJogo[1])}
                "4" -> {darComida();tocador.mudar(musicasJogo[1])}
                "3" -> {brincar(); tocador.mudar(musicasJogo[1])}
                "2"-> {estoque(); tocador.mudar(musicasJogo[1])}
                "1" -> {procurarComida(); tocador.mudar(musicasJogo[1])}
                "0" -> {queroSair = true; tocador.mudar(musicasJogo[1])}
            }
        }
    fun brincar(){
        println("-".repeat(84))
        famili.forEachIndexed { index, comida ->
            println("${index + 1} - ${comida.caracteristicas.nome}")
        }
        println("0 - Sair")
        println("Escolha um gochi para dar carinho")
        ocupado = true
        var r = readln()
        ocupado = false
        if(passadia){
            passaardia()

            passadia = false
            return
        }
        while(r.toIntOrNull() !in 0..famili.size){ // verificação da resposta
            println("Escolha um gochi,somente números da lista")
            ocupado = true
            r = readln()
            ocupado = false
            if(passadia){
                passaardia()

                passadia = false
                return
            }
        }
        if(r.toInt() > 0){
            if(famili.get(r.toInt() - 1).emocao!!.emocaoString != listaDeTipoDeEmocoes[3] && famili.get(r.toInt() - 1).emocao!!.emocaoString != listaDeTipoDeEmocoes[4]){
                famili.get(r.toInt() - 1).brincadeira()
                if(!dinamico){
                    acao = true}
            }else{
                    println("${famili.get(r.toInt() -1).caracteristicas.nome} está dormindo")
                return
            }

        }else{
            return
        }
        var numeroaleatorio = Random.nextInt(0,diaADia)
        if(diaADia in 1..10){
            brincar()
        }

    }
    fun loja(){
        ocupado = true
        println("-".repeat(84))
println("Dinheiro: $dinheiro")
        println("1"+" ".repeat(35)+"Videojogo - deixa o Gochi dependente (não foge) e deixa ele feliz, mas tem consequências   5")
        println("2"+" ".repeat(35)+"Remédio - cura qualquer doença comum até a velhice                                         5")
        println("3"+" ".repeat(35)+"Vacina - Protege o Gochi até a velhice contra doenças                                     15")
        println("4"+" ".repeat(35)+"Acariciador automático - brinca com o gochi automaticamente                               15")
        println("5"+" ".repeat(35)+"Bolo de prestígio - restaura toda a vida, fome e felicidade                               10")
        println("6"+" ".repeat(35)+"Brinquedo - Diminui o dano de felicidade                                                     10")
        println("7"+" ".repeat(35)+"Acordo com a  morte - Seu Gochi não toma mais dano, mas haverá consequências              20")
        println("8"+" ".repeat(35)+"Mapa de comida - Faz você ter mais chance de conseguir comidas melhores                   15")
        println("9"+" ".repeat(35)+"Rastreador de comida - Faz você conseguir só as melhores comidas                          30")
        println("0"+" ".repeat(35)+"Sair")
        ocupado = true
        var resp = readln()
        ocupado = false
        if(passadia){
            passaardia()

            passadia = false
            return
        }
        while(resp.toIntOrNull() !in 0..9){
            println("Escreva um número dentro da lista")
            ocupado = true
            resp = readln()
            ocupado = false
            if(passadia){
                passaardia()

                passadia = false
                return
            }
        }
if(resp == "1"&& dinheiro - 5 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.caracteristicas.nome}")
    }
    println("0 - Sair")
    println("Escolha um gochi para dar o videojogo")
    ocupado = true
    var r = readln()
    ocupado = false
    if(passadia){
        passaardia()

        passadia = false
        return
    }
    while(r.toIntOrNull() !in 0..famili.size){
        println("Escolha um gochi para dar o videojogo,somente números da lista")
        ocupado = true
        r = readln()
        ocupado = false
        if(passadia){
            passaardia()

            passadia = false
            return
        }
    }
    if(r.toInt() >= 1){
    index = r.toInt() -1
    famili.get(index).videojogo = true; famili.get(index).contadorDeAbstinenciadeVideojogos = 0}else if (r.toInt() == 0){
        return
    }
    dinheiro -= 5
}else if (resp == "2" && dinheiro - 5 >= 0){
    var index : Int
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.caracteristicas.nome}")
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
        dinheiro -= 5
    }else if (r.toInt() == 0){
        return
    }else{
        println("Você já comprou o acariciador para o gochi")
loja()
    }

}else if (resp == "3" && dinheiro - 15 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.caracteristicas.nome}")
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
        dinheiro -= 15
    }else if (r.toInt() == 0){
        return
    }else{
        println("Você já deu a vacina para o gochi")
        loja()
    }
}else if (resp == "4" && dinheiro - 15 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.caracteristicas.nome}")
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
        dinheiro -= 15
    }else if (r.toInt() == 0){
        return
    }else{
        println("Você já comprou o acariciador para o gochi")
        loja()
    }

}else if (resp == "5" && dinheiro - 10 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.caracteristicas.nome}")
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
        dinheiro -= 10
    }else if (r.toInt() == 0){
        return
    }else{
        println("Você já deu um bolo de prestígio para o gochi")
        loja()
    }
}else if (resp == "6" && dinheiro - 10 >= 0){
    var index = 0
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.caracteristicas.nome}")
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
        dinheiro -= 10
    }else if (r.toInt() == 0){
        return
    }else{
        println("O gochi já tem um brinquedo")
    loja()
    }
}else if (resp == "7" && dinheiro - 20 >= 0){
    var index : Int
    famili.forEachIndexed { index, gochi ->
        println("${index+1} - ${gochi.caracteristicas.nome}")
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
        dinheiro -= 20
    }else if (r.toInt() == 0){
     return
    }else{
        println("O gochi já fez um acordo com a morte")
        loja()
    }
}else if (resp == "8" && dinheiro - 15 >= 0) {
    if(!mapaDeComida){
    println("Você comprou o mapa de comida")
    mapaDeComida = true
    dinheiro -= 15}else{
        println("Você já tem o mapa de comida")
    }
}else if (resp == "9" && dinheiro - 30 >= 0) {
    if(!rastreadorDeComida){
   println("Você comprou o rastreador de comida")
    rastreadorDeComida = true
    dinheiro -= 30}else{
        println("Você já tem o rastreador de comida")
    }
}
    }
    fun estoque(){
        ocupado = true
        println("-".repeat(84))

        estoque.forEachIndexed { index, comida ->
            println("${index+1} - ${comida.nome}")
        }
        readln()
    }

    fun passaardia(){
        var index = 0
        println("-".repeat(84))
        while(index < famili.size){
            famili.get(index).passardia()
            index++
        }
        dia++
        diaADia = Random.nextInt(1,101)
        if(dia % 4 == 0){
            tempo()
        }
eventospecial()
        acao = false
    }


}

fun main() {
    var save = File("./Assets/Save.txt")
    save.forEachLine() { frase ->
        var numero= ""
        frase.forEach {
            if(it.isDigit()){
                numero += it
            }
        }
        diaRecord += numero.toInt()
    }
    pastamusicaJogo.forEach{ arquivo ->
        musicasJogo.add(arquivo.path)

    }
    tocador.play(musicasJogo[0])
    tocador.diminuir()
    var tam = false
    var color = false
    var dinamico = false
    if(diaRecord > 50){
        bloqueio = 0
    }
    titulo()
    println("\nVocê quer a jogar com janelas grandes? (S/N)")
    var r = readln()
    r= verificadorSEN(r)



    when(r!!.uppercase().replace(" ", "")){
        "S" -> tam = true

        "N" -> tam = false
    }
    println("Você quer a jogar com cores? (S/N)")
    r = readln()
    r= verificadorSEN(r)



    when(r!!.uppercase().replace(" ", "")){
        "S" -> color = true
        "N" -> color = false

    }
    println("Você quer a jogar de maneira temporal/dinâmica? (S/N)")
    r = readln()
    r= verificadorSEN(r)


    when(r!!.uppercase().replace(" ", "")){
        "S" -> dinamico = true
        "N" -> dinamico = false

    }
    tocador.stop()
    jogoTama(tam,color,dinamico)
    while (true){
    save.writeText("diaRecord = ${diaRecord+dia}")
    println("Quer continuar? (S/N)")
    r = readln()

        r= verificadorSEN(r)



        when(r!!.uppercase().replace(" ", "")){
        "S" -> jogoTama(tam,color,dinamico)
        "N" -> break

    }}





}
fun verificadorSEN(valor : String):String{
    var r = valor
    while (r!!.uppercase().replace(" ", "") != "S" && r!!.uppercase().replace(" ","") != "N"){
        println("Escreva somente S ou N")
        r = readln()

    }
    return(r)
}


fun titulo(){
    println(" / __|  ___    (_)  __ _    | |__   ___   _ __    ___  __ __ (_)  _ _    __| |  ___   \n" +
            " \\__ \\ / -_)   | | / _` |   | '_ \\ / -_) | '  \\  |___| \\ V / | | | ' \\  / _` | / _ \\  \n" +
            " |___/ \\___|  _/ | \\__,_|   |_.__/ \\___| |_|_|_|        \\_/  |_| |_||_| \\__,_| \\___/  \n" +
            "             |__/                                                                     \n" +
            "                                                                                      \n" +
            "  __ _   ___                                                                          \n" +
            " / _` | / _ \\                                                                         \n" +
            " \\__,_| \\___/                                                                         \n" +
            "                                                                                      \n" +
            "  _____                                   _   _                                       \n" +
            " |_   _|  __ _   _ __    __ _   ___  __  (_) (_)                                      \n" +
            "   | |   / _` | | '  \\  / _` | (_-< / _| | | | |                                      \n" +
            "   |_|   \\__,_| |_|_|_| \\__,_| /__/ \\__| |_| |_|      ")

}


