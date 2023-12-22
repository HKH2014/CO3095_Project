package com.example.auctionhouse_webapplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.example.auctionhouse_webapplication.dto.AuctionDto;
import com.example.auctionhouse_webapplication.model.Auction;
import com.example.auctionhouse_webapplication.model.Bid;
import com.example.auctionhouse_webapplication.model.User;
import com.example.auctionhouse_webapplication.repo.AuctionRepository;
import com.example.auctionhouse_webapplication.repo.BidRepository;
import com.example.auctionhouse_webapplication.repo.UserRepository;
import com.example.auctionhouse_webapplication.service.AuctionService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuctionControllerTest {

    private static final String AUCTIONS_BASE_PATH = "/auctions";
    private static final String SET_MAX_BID_PATH = "/set-max-bid";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private UserRepository userRepository;
    private User createdUser;

    @BeforeEach
    public void setUp() {
        bidRepository.deleteAll();
        auctionRepository.deleteAll();
        userRepository.deleteAll();

        createdUser = prepareTestUser();
        userRepository.save(createdUser);
    }

    @Test
    @WithMockUser(authorities = "BUYER", username = "test") // Symuluje zalogowanego użytkownika z uprawnieniem BUYER
    public void shouldBeatTheOfferByMinBetIfMaxBidIsGreaterThanCurrentPrice() throws Exception {
        final Auction savedAuction = saveAuction(100, 5);

        mockMvc.perform(post(AUCTIONS_BASE_PATH + SET_MAX_BID_PATH).with(csrf())
                            .param("auctionId", savedAuction.getId().toString())
                            .param("maxBid", "200.0"))// Dodaje token
            .andExpect(status().is3xxRedirection()); // Zmieniamy oczekiwanie na przekierowanie

        final List<Bid> bids = bidRepository.findAll();
        Assertions.assertEquals(1, bids.size());
        Assertions.assertEquals(105.0, bids.get(0).getCurrentBid());
    }

    @Test
    @WithMockUser(authorities = "BUYER", username = "test")
    public void shouldNotAllowBidWithMaxValueLowerThanCurrentPricePlusMinBet() throws Exception {
        final Auction savedAuction = saveAuction(200, 5);

        mockMvc.perform(post(AUCTIONS_BASE_PATH + SET_MAX_BID_PATH).with(csrf())
                            .param("auctionId", savedAuction.getId().toString())
                            .param("maxBid", "204.0"))// Dodaje token
            .andExpect(status().is4xxClientError());// Zmieniamy oczekiwanie na przekierowanie

        final List<Bid> bids = bidRepository.findAll();
        Assertions.assertEquals(0, bids.size());
    }

    @Test
    @WithMockUser(authorities = "BUYER", username = "test")
    public void shouldNotAllowBiddingOnExpiredAuction() throws Exception {
        final Auction savedAuction = saveAuction(200, 5, LocalDate.of(2020, 10, 10));

        mockMvc.perform(post(AUCTIONS_BASE_PATH).with(csrf())
                            .param("auctionId", savedAuction.getId().toString())
                            .param("maxBid", "204.0"))// Dodaje token
            .andExpect(status().is4xxClientError());// Zmieniamy oczekiwanie na przekierowanie

        final List<Bid> bids = bidRepository.findAll();
        Assertions.assertEquals(0, bids.size());
    }

    @Test
    @WithMockUser(authorities = "BUYER", username = "test")
    public void shouldReturnOnlyNonExpiredAuctions() throws Exception {
        saveAuction(200, 5, LocalDate.of(2020, 10, 10));
        saveAuction(200, 5, LocalDate.of(2021, 10, 10));
        saveAuction(10, 2);
        saveAuction(50, 2);

        mockMvc.perform(get(AUCTIONS_BASE_PATH).with(csrf())).andExpect(status().isOk()).andExpect(matcher -> {
            final Map<String, Object> model = matcher.getModelAndView().getModel();
            final Object objectAuctions = model.get("auctions");
            final List<AuctionDto> auctions = (List<AuctionDto>) objectAuctions;
            Assertions.assertEquals(2, auctions.size());
        });

        final List<Bid> bids = bidRepository.findAll();
        Assertions.assertEquals(0, bids.size());
    }

    private User prepareTestUser() {
        final User user = new User();
        user.setUsername("test");
        user.setPassword("");
        return user;
    }

    private Auction saveAuction(final double basePrice, final double minBet) {
        return saveAuction(basePrice, minBet, LocalDate.now().plusDays(1));
    }

    private Auction saveAuction(final double basePrice, final double minBet, final LocalDate endDate) {
        Auction auction = new Auction();
        auction.setEndDate(endDate);
        auction.setBasePrice(basePrice);
        auction.setMinBet(minBet);
        auction.setName("test");
        auction.setSeller(createdUser);
        return auctionRepository.save(auction);
    }

}
