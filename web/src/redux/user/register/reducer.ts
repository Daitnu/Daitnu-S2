import { REGISTER } from './actions';
import { makeApiReducer } from '~/redux/util';
import { ResponseRegister } from '~/@types/response/user';
import { RegisterParam } from '~/@types/request/user';

const userRegisterReducer = makeApiReducer<ResponseRegister, RegisterParam>(REGISTER);

export default userRegisterReducer;
