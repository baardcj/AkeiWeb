/*[...]
@SpringBootTest                                                            #1
@AutoConfigureMockMvc                                                      #2
@Import(FlightBuilder.class)                                               #3
public class RestApplicationTest {

    @Autowired                                                             #4
    private MockMvc mvc;                                                   #4

    @Autowired                                                             #5
    private Flight flight;                                                 #5

    @Autowired                                                             #5
    private Map<String, Country> countriesMap;                             #5

    @MockBean                                                              #6
    private PassengerRepository passengerRepository;                       #6

    @MockBean                                                              #6
    private CountryRepository countryRepository;                           #6

    @Test
    void testGetAllCountries() throws Exception {
        when(countryRepository.findAll()).thenReturn(new                   #7
                ArrayList<>(countriesMap.values()));                        #7
        mvc.perform(get("/countries"))                                     #8
                .andExpect(status().isOk())                                     #9
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))   #9
                .andExpect(jsonPath("$", hasSize(3)));                          #9

        verify(countryRepository, times(1)).findAll();                     #10
    }

    @Test
    void testGetAllPassengers() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new                 #11
                ArrayList<>(flight.getPassengers()));                         #11

        mvc.perform(get("/passengers"))                                    #12
                .andExpect(status().isOk())                                     #12
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))   #12
                .andExpect(jsonPath("$", hasSize(20)));                         #12

        verify(passengerRepository, times(1)).findAll();                   #13
    }

    @Test
    void testPassengerNotFound() {
        Throwable throwable = assertThrows(NestedServletException.class,   #14
        () -> mvc.perform(get("/passengers/30"))                 #14
                .andExpect(status().isNotFound()));         #14
        assertEquals(PassengerNotFoundException.class,                     #15
                throwable.getCause().getClass());                     #15
    }

    @Test
    void testPostPassenger() throws Exception {

        Passenger passenger = new Passenger("Peter Michelsen");            #16
        passenger.setCountry(countriesMap.get("US"));                      #16
        passenger.setIsRegistered(false);                                  #16
        when(passengerRepository.save(passenger))                          #16
                .thenReturn(passenger);                                        #16

        mvc.perform(post("/passengers")                                    #17
                .content(new ObjectMapper().writeValueAsString(passenger))     #17
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)) #17
                .andExpect(status().isCreated())                                #17
                .andExpect(jsonPath("$.name", is("Peter Michelsen")))           #17
                .andExpect(jsonPath("$.country.codeName", is("US")))            #17
                .andExpect(jsonPath("$.country.name", is("USA")))               #17
                .andExpect(jsonPath("$.registered", is(Boolean.FALSE)));        #17

        verify(passengerRepository, times(1)).save(passenger);                  #18
    }

    @Test  void testPatchPassenger() throws Exception {
        Passenger passenger = new Passenger("Sophia Graham");                #19
        passenger.setCountry(countriesMap.get("UK"));                        #19
        passenger.setIsRegistered(false);                                    #19
        when(passengerRepository.findById(1L))                               #19
                .thenReturn(Optional.of(passenger));                            #19
        when(passengerRepository.save(passenger))                            #20
                .thenReturn(passenger);                                         #20
        String updates =
                "{\"name\":\"Sophia Jones\", \"country\":\"AU\",                   ㉑
         \"isRegistered\":\"true\"}";                                      ㉑

        mvc.perform(patch("/passengers/1")                                   ㉑
                .content(updates)                                                ㉑
          .header(HttpHeaders.CONTENT_TYPE,                                ㉑
                MediaType.APPLICATION_JSON))                 ㉑
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))    ㉑
          .andExpect(status().isOk());

        verify(passengerRepository, times(1)).findById(1L);                  ㉒
        verify(passengerRepository, times(1)).save(passenger);               ㉒
    }

    @Test  public void testDeletePassenger() throws Exception {

        mvc.perform(delete("/passengers/4"))                                 ㉓
             .andExpect(status().isOk());                                  ㉓

        verify(passengerRepository, times(1)).deleteById(4L);                ㉔
    }

}*/