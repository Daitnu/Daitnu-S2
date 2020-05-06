import { LOGIN } from './actions';
import { makeApiReducer } from '~/redux/util';
import { ResponseLogin } from '~/@types/response/user';
import { LoginParam } from '~/@types/request/user';

const userLoginReducer = makeApiReducer<ResponseLogin, LoginParam>(LOGIN);

export default userLoginReducer;
