import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

const RoutesWrapper = ({ children }) => {
  const location = useLocation();

  useEffect(() => {
    // need to send request whenever user change page so as to check authentication.
    console.log(location);
  }, [location]);

  return children;
};

export default RoutesWrapper;
