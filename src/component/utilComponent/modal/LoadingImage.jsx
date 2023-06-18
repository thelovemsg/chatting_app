import { useRef, useState, useEffect } from 'react';
import PropTypes from 'prop-types';

const ImageHolder = ({ src, style }) => {
  const imgRef = useRef(null);
  const observerRef = useRef(null);
  const [isLoad, setIsLoad] = useState(false);

  function onIntersection(entries, io) {
    entries.forEach((entry) => {
      if (entry.isIntersecting) {
        io.unobserve(entry.target);
        setIsLoad(true);
      }
    });
  }

  useEffect(() => {
    if (!observerRef.current) {
      observerRef.current = new IntersectionObserver(onIntersection, {
        threshold: 0.5, // Trigger when half of the image is visible
      });
    }

    if (imgRef.current) {
      observerRef.current.observe(imgRef.current);
    }
  }, []);

  return (
    <img
      ref={imgRef}
      className="profile-image-carousel"
      src={isLoad ? src : '/img/img_place_holder.png'}
      style={style}
      alt=""
    />
  );
};

ImageHolder.propTypes = {
  src: PropTypes.string,
  style: PropTypes.objectOf(PropTypes.string).isRequired,
};

ImageHolder.defaultProps = {
  src: null,
};

export default ImageHolder;
