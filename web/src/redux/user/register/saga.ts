import { all, takeLatest } from 'redux-saga/effects';
import { REGISTER_REQUEST, REGISTER } from './actions';
import { makeApiCallSagaFunc } from '~/redux/util';
import UserApi from '~/library/request/UserApi';

const api = new UserApi();
const register$ = makeApiCallSagaFunc({ type: REGISTER, apiFunc: api.register.bind(api) });

export function* userRegisterSaga() {
  yield all([takeLatest(REGISTER_REQUEST, register$)]);
}
