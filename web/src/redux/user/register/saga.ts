import { all, takeLatest } from 'redux-saga/effects';
import { REGISTER_REQUEST, REGISTER } from './actions';
import { makeApiCallSagaFunc } from '~/redux/util';

const register$ = makeApiCallSagaFunc(REGISTER);

export function* userRegisterSaga() {
  yield all([takeLatest(REGISTER_REQUEST, register$)]);
}
