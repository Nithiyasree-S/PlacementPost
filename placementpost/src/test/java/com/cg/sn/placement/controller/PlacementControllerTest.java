package com.cg.sn.placement.controller;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cg.sn.placement.Controller.PlacementController;
import com.cg.sn.placement.Entity.Placement;
import com.cg.sn.placement.Service.PlacementService;




@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {PlacementController.class,})
public class PlacementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PlacementService placementService;

    @InjectMocks
    private PlacementController placementController;

    @Test
    public void testGetAllPlacements() throws Exception {
        // Prepare test data
        Placement placement1 = new Placement();
        placement1.setId(1L);
        placement1.setTitle("Placement 1");

        Placement placement2 = new Placement();
        placement2.setId(2L);
        placement2.setTitle("Placement 2");

        List<Placement> placements = Arrays.asList(placement1, placement2);

        // Mock the service method
        Mockito.when(placementService.getAllPlacements()).thenReturn(placements);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/placement/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title", Matchers.is("Placement 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].title", Matchers.is("Placement 2")));

        // Verify that the service method was called
        Mockito.verify(placementService, Mockito.times(1)).getAllPlacements();
    }

    @Test
    public void testAddPlacement() {
        // Create a Placement object with test data
        Placement placement = new Placement();
        placement.setTitle("Placement Title");
        placement.setCompany("Placement Company");
        // Set other properties of the placement object

        // Mock the behavior of the placementService.addPlacement method
        Placement addedPlacement = new Placement();
        addedPlacement.setId(1L);
        addedPlacement.setTitle(placement.getTitle());
        addedPlacement.setCompany(placement.getCompany());
        // Set other properties of the addedPlacement object
        when(placementService.addPlacement(any(Placement.class))).thenReturn(addedPlacement);

        // Perform the POST request to the "/add" endpoint
        given()
                .contentType(io.restassured.http.ContentType.JSON)
                .body(placement)
                .when()
                .post("/add")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(io.restassured.http.ContentType.JSON)
                .body("data.id", Matchers.equalTo(addedPlacement.getId().intValue()))
                .body("data.title", Matchers.equalTo(addedPlacement.getTitle()))
                .body("data.company", Matchers.equalTo(addedPlacement.getCompany()));
        // Add additional assertions as needed for other properties of the response

        // Verify that the placementService.addPlacement method was called with the correct argument
        verify(placementService).addPlacement(eq(placement));
    }

}
