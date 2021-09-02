package com.metricalsky.backlogged.backend.activity.entity;

import java.time.Duration;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.metricalsky.backlogged.backend.common.converters.DurationInMinutesConverter;

@Entity
@DiscriminatorValue("TIME")
public class TimeActivity extends Activity {

    @Column(name = "minutes")
    @Convert(converter = DurationInMinutesConverter.class)
    private Duration duration;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
