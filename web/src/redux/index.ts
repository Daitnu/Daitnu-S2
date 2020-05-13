import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import user from './global/user';
import userRegister, { userRegisterSaga } from './user/register';
import userLogin, { userLoginSaga } from './user/login';
import categoryGet, { categoryGetSaga } from './category/get';

const rootReducer = combineReducers({ user, userRegister, userLogin, categoryGet });

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;

export function* rootSaga() {
  yield all([userRegisterSaga(), userLoginSaga(), categoryGetSaga()]);
}
