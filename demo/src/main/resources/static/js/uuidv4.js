function uuidv4() {
  return "10000000-1000-4000-8000-100000000000".replace(/[018]/g, (c) =>
    (
      +c ^
      (crypto.getRandomValues(new Uint8Array(1))[0] & (15 >> (+c / 4)))
    ).toString(16)
  );
}

function uuidv4Int() {
  const array = new Uint32Array(2);
  crypto.getRandomValues(array);
  return array[0] * 0x200000 + (array[1] >>> 11);
}
