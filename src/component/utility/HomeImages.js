import React, { useState } from "react";
import Carousel from "react-bootstrap/Carousel";
import { Trans } from "react-i18next";

function ControlledCarousel() {
  const [index, setIndex] = useState(0);

  const handleSelect = (selectedIndex, e) => {
    setIndex(selectedIndex);
  };

  return (
    <Carousel activeIndex={index} onSelect={handleSelect}>
      <Carousel.Item>
        <img
          className="d-block w-40 carousel-image"
          src="./img/kakaoFriends.png"
          alt="First slide"
        />
        <Carousel.Caption>
          <h3 className="carousel-caption">
            <Trans i18nKey="carousel.carousel_image_1_title" />
          </h3>
          <Trans i18nKey="carousel.carousel_image_1_description" />
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-40 carousel-image"
          src="./img/lion.png"
          alt="Second slide"
        />

        <Carousel.Caption>
          <h3 className="carousel-caption">
            <Trans i18nKey="carousel.carousel_image_2_title" />
          </h3>
          <Trans i18nKey="carousel.carousel_image_2_description" />
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-40 carousel-image"
          src="./img/neo.png"
          alt="Third slide"
        />
        <Carousel.Caption>
          <h3 className="carousel-caption">
            <Trans i18nKey="carousel.carousel_image_3_title" />
          </h3>
          <Trans i18nKey="carousel.carousel_image_3_description" />
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
  );
}

export default ControlledCarousel;
