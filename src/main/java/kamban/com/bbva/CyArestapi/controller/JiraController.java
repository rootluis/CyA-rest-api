package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.Persona;
import kamban.com.bbva.CyArestapi.model.Producto;
import org.bson.codecs.jsr310.LocalDateCodec;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jira/rest/")
public class JiraController {

    @GetMapping("/v1")
    public Map<String, Object> getToken() {
        String uri = "https://globaldevtools.bbva.com/jira/plugins/servlet/oauth/users/{urlParameter}?response_type={responseType}&client_id={clientId}&redirect_uri={redirectUri}";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("urlParameter", "access-tokens");
        uriVariables.put("responseType", "token");
        uriVariables.put("clientId", "luismiguel.mejia.contractor");
        uriVariables.put("redirectUri", "https://globaldevtools.bbva.com/jira/plugins/servlet/oauth/users/access-tokens");
//
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
////Add the Jackson Message converter
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//
//// Note: here we are making this converter to process any kind of response,
//// not only application/*json, which is the default behaviour
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);

        ResponseEntity<Map> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Map.class, uriVariables);

        return response.getBody();
    }

    @GetMapping("/v2")
    public HttpEntity<String> getHttpEntity() {
//        String uri = "https://globaldevtools.bbva.com/jira/plugins/servlet/oauth/users/{urlParameter}?response_type={responseType}&client_id={clientId}&redirect_uri={redirectUri}";

        String uri = "https://globaldevtools.bbva.com/jira/plugins/servlet/oauth/users/access-tokens";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("response_type", "token")
                .queryParam("client_id", "luismiguel.mejia.contractor")
                .queryParam("redirect_uri", "https://globaldevtools.bbva.com/jira/plugins/servlet/oauth/users/access-tokens");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        return response;
    }

    public static void main(String[] args) {
        Persona p1 = new Persona(1, "Luis", "Mejia", LocalDate.of(1985, 12, 31), "Masculino", "NTTDATA");
        Persona p2 = new Persona(2, "Leslie", "Hernandez", LocalDate.of(1986, 6, 9), "Femenino", "NTTDATA");
        Persona p3 = new Persona(3, "Valeria", "Mejia", LocalDate.of(2013, 8, 11), "Femenino", "NTTDATA");
        Persona p4 = new Persona(4, "Vanessa", "Hernandez", LocalDate.of(1980, 1, 14), "Femenino", "NTTDATA");
        Persona p5 = new Persona(5, "Angel", "Diaz", LocalDate.of(1995, 7, 11), "Masculino", "Everis");
        Persona p6 = new Persona(6, "Paola", "Chavez", LocalDate.of(2008, 2, 7), "Femenino", "GFT");
        Persona p7 = new Persona(7, "Erick", "Espinoza", LocalDate.of(1981, 9, 22), "Masculino", "GFT");
        Persona p8 = new Persona(8, "Efren", "Garcia", LocalDate.of(2020, 11, 20), "Masculino", "Everis");

        Producto pr1 = new Producto(1001, "Jamon", "Embutido de puerco y pavo", LocalDate.of(2022, 1, 1), true, 10.50);
        Producto pr2 = new Producto(1002, "Cuchillo", "Cuchillo para cortor pan", LocalDate.of(2000, 6, 15), false, 25222.75);
        Producto pr3 = new Producto(1003, "Queso", "Lacteo de vaca", LocalDate.of(2022, 10, 20), true, 847.66);
        Producto pr4 = new Producto(1004, "Salchicha", "Embutido de pavo", LocalDate.of(2022, 7, 20), true, 158.14);
        Producto pr5 = new Producto(1005, "Topper", "Recipiente para almacenar", LocalDate.of(2000, 11, 24), false, 487.14);
        Producto pr6 = new Producto(1006, "Boligrafo", "Boligrafo color negro", LocalDate.of(2005, 4, 26), false, 458.69);
        Producto pr7 = new Producto(1007, "Celular", "Telefono celular", LocalDate.of(1999, 8, 3), false, 6541.54);
        Producto pr8 = new Producto(1008, "Tortilla", "Tortilla de maiz", LocalDate.of(2022, 7, 20), true, 345.45);
        Producto pr9 = new Producto(1009, "Manzana", "Mazana de temporada", LocalDate.of(2022, 6, 17), true, 5448.69);
        Producto pr10 = new Producto(1010, "Lentes", "Lentes contro el Sol", LocalDate.of(2007, 4, 9), false, 548.58);


        List<Persona> personas = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
        List<Producto> productos = Arrays.asList(pr1, pr2, pr3, pr4, pr5, pr6, pr7, pr8, pr9, pr10);

//        for (Persona p: personas) {
//            System.out.println(p);
//        }

//        personas.forEach(p -> System.out.println(p));
//        personas.forEach(System.out::println);

        //  1.- Filter (param : predicate)
//        List<Persona> personaList = personas.stream()
//                .filter(p -> JiraController.getEdad(p.getFechaNacimiento()) > 30)
//                .collect(Collectors.toList());
//        JiraController.printLista(personaList);


        // 2.- Map (param: Function)
//        List<Integer> edades = personas.stream()
//                .map(e -> JiraController.getEdad(e.getFechaNacimiento()))
//                .collect(Collectors.toList());
        //        JiraController.printLista(edades);

//        List<String> edades = personas.stream()
//                .map(e -> "Desarrollador " + e.getNombre())
//                .collect(Collectors.toList());
//        JiraController.printLista(edades);

//        Function<String, String> coderFunction = name -> "Coder con Function " + name;
//        List<String> developers = personas.stream()
//                //.map(n -> n.getNombre())
//                .map(Persona::getNombre)
//                .map(coderFunction)
//                .collect(Collectors.toList());
//
//        JiraController.printLista(developers);
//============================================================================================================================================================================
        // 3.- Sorted (param: Comparator)
        //Ascendente
//        Comparator<Persona> personaComparatorAsc = (Persona objPersona1, Persona objPersona2) -> objPersona1.getNombre().compareTo(objPersona2.getNombre());
//        Comparator<Persona> personaComparatorAsc = Comparator.comparing(Persona::getNombre);

        //Descendente
//        Comparator<Persona> personaComparatorDesc = (Persona objPersona1, Persona objPersona2) -> objPersona2.getNombre().compareTo(objPersona1.getNombre());
//        List<Persona> listaOrdenada = personas.stream()
//                .sorted(personaComparatorDesc)
//                .collect(Collectors.toList());
//        JiraController.printLista(listaOrdenada);
//        ============================================================================================================================================================================
        //Ascendente
//        Comparator<Persona> compareForFecNacAsc = (Persona per1, Persona per2) -> per1.getFechaNacimiento().compareTo(per2.getFechaNacimiento());
//        Comparator<Persona> compareForFecNacAsc = Comparator.comparing(Persona::getFechaNacimiento);

        //Descendente
//        Comparator<Persona> compareForFecNacDesc = (Persona per1, Persona per2) -> per2.getFechaNacimiento().compareTo(per1.getFechaNacimiento());
//        List<Persona> listaOrdenada = personas.stream()
//                .sorted(compareForFecNacDesc)
//                .collect(Collectors.toList());
//        JiraController.printLista(listaOrdenada);
//============================================================================================================================================================================
        // 4.- Match (param: Predicate)

        Predicate<Persona> predicateStartWith = people -> people.getNombre().startsWith("A");
        // 4.1.- anyMatch  No evalua todo el stream termina en la coincidencia
        boolean rpta1 = personas.stream()
//                .anyMatch(p -> p.getNombre().startsWith("V"));
                .anyMatch(predicateStartWith);
//        System.out.println(rpta1);
//        ============================================================================================================================================================================
        // 4.2.- allMatch  Evalua todo el stream bajo la condicion, es decir recorre todos los registros a evaluar y que estos complan con el criterio definido
        boolean rpta2 = personas.stream()
//                .allMatch(p -> p.getNombre().startsWith("A"));
                .anyMatch(predicateStartWith);
//        System.out.println(rpta2);
//        ============================================================================================================================================================================
        // 4.3.- allMatch  Evalua todo el stream bajo la condicion, es decir recorre todos los registros a evaluar y que estos complan con el criterio definido
        boolean rpta3 = personas.stream()
//                .noneMatch(p -> p.getNombre().startsWith("G"));
                .anyMatch(predicateStartWith);
//        System.out.println(rpta3);
//        ============================================================================================================================================================================
        // 5.- Skip/Limit
        // 5.1.- Skip: ignora los registros definidos en el .skip()
        List<Producto> list1 = productos.stream()
                .skip(4)
                .collect(Collectors.toList());
//        JiraController.printLista(list1);

        // 5.2.- Limit: toma unicamente los registros definidos en .limit()
        List<Producto> list2 = productos.stream()
                .limit(4)
                .collect(Collectors.toList());
//        JiraController.printLista(list2);

        // 5.3.- Skip y Limit, este puede ser un ejemplo de paginacion
        int pageNumber = 4;
        int pageSize = 2;
        List<Producto> list3 = productos.stream()
                .skip(pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
//        JiraController.printLista(list3);

//============================================================================================================================================================================
        // 6.- Collectors
        // 6.1.- GroupBy
        Map<Boolean, List<Producto>> listMap1 = productos.stream()
                .filter(p -> p.isPerecedero())
                .collect(Collectors.groupingBy(Producto::isPerecedero));
//        System.out.println(listMap1.entrySet());
//        System.out.println(listMap1.values());

        Map<String, List<Persona>> listMap2 = personas.stream()
                .collect(Collectors.groupingBy(Persona::getEmpresa));
//        System.out.println(listMap2.entrySet());
//        System.out.println(listMap2.values());
//        ============================================================================================================================================================================
        // 6.2.- Counting
        Map<String, Long> listMap3 = personas.stream()
                .collect(Collectors.groupingBy(Persona::getEmpresa, Collectors.counting()));
//        System.out.println(listMap3);
//        ============================================================================================================================================================================
        // 6.3.- Agrupando por Tipo Producto y sumando precios
        Map<Boolean, Double> valDauble = productos.stream()
                .collect(Collectors.groupingBy(p -> p.isPerecedero(), Collectors.summingDouble(Producto::getPrecio)));
//        System.out.println(valDauble);

        // 6.4.- Agrupando por Suma y Resumen
        DoubleSummaryStatistics staticss = productos.stream()
                .collect(Collectors.summarizingDouble(Producto::getPrecio));
//        System.out.println(staticss);

        // 6.5.- Reduce
        Optional<Double> optProduct = productos.stream()
                .map(Producto::getPrecio)
                .reduce(Double::sum);
        System.out.println(optProduct); //40069.140000
    }


    private static int getEdad(LocalDate localDate) {
        return Period.between(localDate, LocalDate.now()).getYears();
    }

    private static void printLista(List<?> list) {
//        list.forEach(o -> System.out.println(0));
        list.forEach(System.out::println);
    }

}
