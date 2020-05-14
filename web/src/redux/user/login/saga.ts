import { all, call, put, takeLatest } from 'redux-saga/effects';
import { LOGIN_REQUEST, LOGIN } from './actions';
import { makeApiCallSagaFunc } from '~/redux/util';
import UserApi from '~/library/request/UserApi';
import { ResponseLogin } from '~/@types/response/user';
import { HTTPResponse } from '~/@types/response/success';
import { saveUser } from '~/redux/global/user';
import { historyPush } from '~/redux/util/history';

const api = new UserApi();
const successCb = function* ({ data: { userId, name } }: HTTPResponse<ResponseLogin>) {
  yield put(saveUser({ id: userId, name, email: `${userId}@daitnu2.com` }));
  yield call(historyPush, '/');
};

const login$ = makeApiCallSagaFunc({ type: LOGIN, apiFunc: api.login.bind(api), successCb });

export function* userLoginSaga() {
  yield all([takeLatest(LOGIN_REQUEST, login$)]);
}
