import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/*
    Explorando a API de data do Java 8
 */
public class ExplorandoAPI {
    public static void main(String[] args) {

        /**
         * Principais classes para manipulação de data
         *
         * LocalDate: Representa uma data sem hora
         * LocalTime: Representa um horário sem data - hh:mm:ss.zzz
         * LocalDateTime: Representa uma data com hora - yyyy-MM-dd-HH-mm-ss.zzz
         * DateTimeFormatter: Classe que possui vários métodos de formatação
         * MonthDay: Representa o dia e o mês, sem o ano.
         * YearMonth: Representa o mês e o ano sem o dia.
         * Period: Representa um período
         * Duration: Representa um intervalo em segundos
         *
         */

        //Criando objetos
        LocalDate today = LocalDate.now();
        System.out.println(today); //2024-05-22

        LocalTime currentTime = LocalTime.now();
        System.out.println(currentTime); //18:29:12.188393139

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now); //2024-05-22T18:29:12.188477960

        //Para encontrar ZoneId: https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html
        LocalDate todayTokyo = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(todayTokyo); //2024-05-23

        LocalDateTime localDateTimeGermany = LocalDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.println(localDateTimeGermany); //2024-05-22T23:34:23.059242252

        //Representação do meio-dia
        LocalTime noon = LocalTime.of(12, 0);
        System.out.println(noon); //12:00

        //Representação do Natal
        LocalDate christmas = LocalDate.of(2024, 12, 25);
        System.out.println(christmas); //2024-12-25

        LocalDate christmas2023 = LocalDate.of(2023, Month.DECEMBER, 25);
        System.out.println(christmas2023); //2023-12-25

        //Representação do Natal com horário
        LocalDateTime christmasTime = LocalDateTime.of(2024,Month.DECEMBER, 25, 12,10);
        System.out.println(christmasTime);

        LocalDateTime christmasNoon = LocalDateTime.of(christmas, noon);
        System.out.println(christmasNoon);

        /**
         * Métodos importantes para manipulação de datas
         *
         * get: obtém o valor de algo (hora, minuto, ano, etc)
         * is: verifica alguma coisa (retorna um booleano)
         * with: como se fosse um setter ("altera" datas), lembrando que datas são imutáveis
         *
         */

        //Explorando get
        System.out.println(christmasNoon.get(ChronoField.DAY_OF_MONTH)); //25
        System.out.println(christmasNoon.getDayOfMonth()); //25

        System.out.println(christmasNoon.get(ChronoField.YEAR)); //2024
        System.out.println(christmasNoon.getYear()); //2024

        System.out.println(christmasNoon.get(ChronoField.MONTH_OF_YEAR)); //12
        System.out.println(christmasNoon.getMonthValue()); //12
        System.out.println(christmasNoon.getMonth()); //DECEMBER

        //Explorando o is
        System.out.println(christmas.isAfter(christmas2023)); //true
        System.out.println(christmasNoon.isBefore(christmasTime)); //true
        System.out.println(christmas.isEqual(christmas2023)); //false

        //lembrando que christmas é um LocalDate
        System.out.println(christmas.isSupported(ChronoUnit.HOURS)); //false - Posso fazer operações usando hora?
        System.out.println(christmas.isSupported(ChronoField.HOUR_OF_DAY)); //false - Essa data tem informação (campo) de hora?

        //Explorando o with
        LocalDate d = LocalDate.of(2024,Month.MAY,22);
        LocalDate d1 = d.withDayOfMonth(23);
        System.out.println(d); //Perceba que a data de d não foi alterada - Imutabilidade
        System.out.println(d1);

        LocalDate dApril = d.with(ChronoField.MONTH_OF_YEAR, Month.APRIL.getValue());
        System.out.println(dApril);

        //Convertendo LocalDateTime para LocalDate ou LocalTime
        LocalDate dateNow = now.toLocalDate(); //lembrando que now é um LocalDateTime
        LocalTime timeNow = now.toLocalTime();
        System.out.println(now); //2024-05-22T19:13:37.548983041
        System.out.println(dateNow); //2024-05-22
        System.out.println(timeNow); //19:13:37.548983041

        //Convertendo LocalDate para LocalDateTime
        LocalDateTime localDateTimeConverted = dateNow.atTime(timeNow);
        System.out.println(localDateTimeConverted); //2024-05-22T19:16:20.668201496

        //Cálculos de intervalo de tempo usando datas
        //Duration - Só consegue mostrar em segundos
        Instant epoch = Instant.EPOCH; //01-01-1970 00:00:00
        Instant instantNow = Instant.now();
        System.out.println(Duration.between(epoch, instantNow).getSeconds()); //1716416560
        System.out.println(Duration.between(christmasNoon, christmasTime).getSeconds()); //600
        //System.out.println(Duration.between(christmasNoon, christmasTime).get(ChronoUnit.MINUTES)); Da uma exception

        //Period - Podemos mostrar em outros formatos que não seja segundos
        LocalDate birthday = LocalDate.of(1984, 8, 18);

        System.out.println(Period.between(birthday, today).get(ChronoUnit.YEARS)); // 39 anos
        System.out.println(Period.between(birthday, today).get(ChronoUnit.MONTHS)); // 9 meses
        System.out.println(Period.between(birthday, today).get(ChronoUnit.DAYS)); // 4 dias

        //Formatando a data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        System.out.println(birthday.format(formatter)); //18 08 1984
        System.out.println(formatter.format(birthday)); //18 08 1984

        String birthdayStr = "18 08 1984";
        LocalDate birthdayLocalDate = LocalDate.parse(birthdayStr, formatter);
        System.out.println(birthdayLocalDate);
    }
}
