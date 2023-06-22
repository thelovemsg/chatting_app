export const sortArrayWithFirstChar = (filteredFriends) =>
  [...filteredFriends].sort((a, b) => {
    const targetA = a.name.charAt(0);
    const targetB = b.name.charAt(0);
    return targetA > targetB ? 1 : -1;
  });

export const sortFriendsByDate = (chattingRoomList) =>
  [...chattingRoomList].sort(
    (a, b) => new Date(b.lastChattingDate) - new Date(a.lastChattingDate)
  );

export const sortArrayWithInput = (list, input) => {
  [...list].name.toLowerCase().includes(input.toLowerCase());
};
