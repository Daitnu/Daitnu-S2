export const USER_ID = 'userId' as const;
export const USER_NAME = 'userName' as const;

const getId = () => sessionStorage.getItem(USER_ID);
const getName = () => sessionStorage.getItem(USER_NAME);

export default { getId, getName };
