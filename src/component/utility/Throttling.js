export default function throttle(func, wait) {
  let previous = 0;

  return function throttledFunction(...args) {
    // Use rest parameters instead of 'arguments'
    const now = Date.now();
    const remaining = wait - (now - previous);
    if (remaining > 0) return;
    previous = now;
    func.apply(this, args); // Use 'args' instead of 'arguments'
  };
}
