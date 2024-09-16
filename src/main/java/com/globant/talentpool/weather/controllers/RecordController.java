package com.globant.talentpool.weather.controllers;

import com.globant.talentpool.weather.models.dtos.ResponseDTO;
import com.globant.talentpool.weather.services.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(description = "The Weather resource allows you to consult and save the weather conditions " +
        "of a specific city or by geographic coordinates",
        name = "Weather resource")
@RestController
@RequestMapping("weather/")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Operation(summary = "Save weather record by city",
            description = "Save weather conditions of a specific city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "${api.response-codes.ok.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) }),

            @ApiResponse(responseCode = "400",
                    description = "${api.response-codes.badRequest.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "${api.response-codes.notFound.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "${api.response-codes.internalServerError.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) })})
    @RequestMapping(value = "/city/{cityName}", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> saveByCity(@PathVariable String cityName){
        return recordService.saveByCity(cityName);
    }

    @Operation(summary = "Save weather record by latitude and longitude",
            description = "Save weather conditions of a specific geographic coordinates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "${api.response-codes.ok.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) }),

            @ApiResponse(responseCode = "400",
                    description = "${api.response-codes.badRequest.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "${api.response-codes.notFound.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "${api.response-codes.internalServerError.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) })})

    @RequestMapping(value = "/latitude/{lat}/longitude/{lon}", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> saveByLatLon(@PathVariable Double lat, @PathVariable Double lon){

        return recordService.saveByLatLong(lat, lon);
    }
}
