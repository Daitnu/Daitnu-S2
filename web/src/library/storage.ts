export const USER_ID = 'userId' as const;
export const USER_NAME = 'userName' as const;

const getId = () => sessionStorage.getItem(USER_ID);
const getName = () => sessionStorage.getItem(USER_NAME);
const getUserInfo = () => ({ [USER_ID]: getId(), [USER_NAME]: getName() });

export default { getId, getName, getUserInfo };
