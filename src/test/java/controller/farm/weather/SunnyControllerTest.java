package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.Cloudy;
import model.weather.Rainy;
import model.weather.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SunnyControllerTest {
    WeatherController weatherController;
    FarmController farmController;
    Weather weather;

    @BeforeEach
    void setUp() {
        this.weatherController = new SunnyController(0);
        this.farmController = Mockito.mock(FarmController.class);
        this.weather = Mockito.mock(Weather.class);
    }

    @Test
    void updateWeatherSameDay() {
        this.weatherController.updateWeather(farmController, weather, 0, 0);

        Mockito.verify(farmController, Mockito.never()).setWeatherController(Mockito.any());
        Mockito.verify(weather, Mockito.never()).setWeatherCondition(Mockito.any());
    }

    @Test
    void updateWeatherSame() {
        this.weatherController.updateWeather(farmController, weather, 1, 0.5);

        Mockito.verify(farmController, Mockito.never()).setWeatherController(Mockito.any());
        Mockito.verify(weather, Mockito.never()).setWeatherCondition(Mockito.any());
    }

    @Test
    void updateWeatherRainy() {
        this.weatherController.updateWeather(farmController, weather, 1, 0.09);

        ArgumentCaptor<RainyController> argumentCaptor = ArgumentCaptor.forClass(RainyController.class);
        Mockito.verify(farmController).setWeatherController(argumentCaptor.capture());
        Assertions.assertEquals(1, argumentCaptor.getValue().getLastDay());

        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Rainy.class));
    }

    @Test
    void updateWeatherCloudy() {
        this.weatherController.updateWeather(farmController, weather, 1, 0.3);

        ArgumentCaptor<CloudyController> argumentCaptor = ArgumentCaptor.forClass(CloudyController.class);
        Mockito.verify(farmController).setWeatherController(argumentCaptor.capture());
        Assertions.assertEquals(1, argumentCaptor.getValue().getLastDay());

        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Cloudy.class));
    }

}
