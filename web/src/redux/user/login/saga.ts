import { all, call, takeLatest } from 'redux-saga/effects';
import { LOGIN_REQUEST, LOGIN } from './actions';
import { makeApiCallSagaFunc } from '~/redux/util';
import UserApi from '~/library/request/UserApi';
import { ResponseLogin } from '~/@types/response/user';
import { HTTPResponse } from '~/@types/response/success';
import { historyPush } from '~/redux/util/history';

const USER_ID = 'userId' as const;
const USER_NAME = 'userName' as const;

const api = new UserApi();
const successCb = function* ({ data: { userId, name } }: HTTPResponse<ResponseLogin>) {
  yield call([sessionStorage, 'setItem'], USER_ID, userId);
  yield call([sessionStorage, 'setItem'], USER_NAME, name);
  yield call(historyPush, '/');
};

const login$ = makeApiCallSagaFunc({ type: LOGIN, apiFunc: api.login.bind(api), successCb });

export function* userLoginSaga() {
  yield all([takeLatest(LOGIN_REQUEST, login$)]);
}
