import { all, call, takeLatest } from 'redux-saga/effects';
import { REGISTER_REQUEST, REGISTER } from './actions';
import { makeApiCallSagaFunc } from '~/redux/util';
import UserApi from '~/library/request/UserApi';
import { push } from '~/redux/util/history';

const api = new UserApi();
const successCb = function* () {
  yield call(push, '/login');
};

const register$ = makeApiCallSagaFunc({
  type: REGISTER,
  apiFunc: api.register.bind(api),
  successCb,
});

export function* userRegisterSaga() {
  yield all([takeLatest(REGISTER_REQUEST, register$)]);
}
