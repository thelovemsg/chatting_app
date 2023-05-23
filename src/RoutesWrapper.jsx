import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

// need to send request whenever user change page so as to check authentication.
const RoutesWrapper = ({ children }) => {
  const location = useLocation();

  useEffect(() => {
    console.log(location);
  }, [location]);

  return children;
};

export default RoutesWrapper;
