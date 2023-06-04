function imageUrlPropType(props, propName, componentName) {
  // Check that the prop is a string.
  if (typeof props[propName] !== 'string') {
    return new Error(`${componentName}: ${propName} should be a string.`);
  }

  // Check that the prop is a valid URL.
  try {
    const newUrl = new URL(props[propName]);
    console.log('newUrl : ', newUrl);
  } catch (e) {
    return new Error(`${componentName}: ${propName} should be a valid URL.`);
  }

  // Check that the URL ends in a typical image extension.
  const validImageExtensions = ['jpg', 'jpeg', 'gif', 'png', 'svg'];
  if (
    !validImageExtensions.some((extension) =>
      props[propName].endsWith(`.${extension}`)
    )
  ) {
    return new Error(
      `${componentName}: ${propName} should be a URL to an image (jpg, jpeg, gif, png, svg).`
    );
  }

  // The prop is valid.
  return null;
}

export default imageUrlPropType;
