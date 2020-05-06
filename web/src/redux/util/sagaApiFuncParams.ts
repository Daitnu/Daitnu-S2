import UserApi from '~/library/request/UserApi';
import { REGISTER } from '../user/register';
import { LOGIN } from '../user/login';

const api = new UserApi();

export default {
  [REGISTER]: api.register.bind(api),
  [LOGIN]: api.login.bind(api),
};
