import axios, { AxiosInstance, AxiosResponse } from 'axios';
import { BusinessErrorResponse } from '@customTypes/response/error';

const API_SERVER = 'http://localhost:8080';
const TYPE = {
  JSON: 'application/json',
};

export class Api {
  private api: AxiosInstance;

  constructor() {
    this.api = axios.create({
      baseURL: API_SERVER,
      headers: {
        'Content-Type': TYPE.JSON,
      },
      timeout: 5000,
    });
  }

  // TODO: error catch
  public get<T, R = AxiosResponse<T>>(url: string): Promise<R | BusinessErrorResponse> {
    return this.api.get(url);
  }

  public post<T, D, R = AxiosResponse<T>>(
    url: string,
    data: D,
  ): Promise<R | BusinessErrorResponse> {
    return this.api.post(url, data);
  }

  public patch<T, D, R = AxiosResponse<T>>(
    url: string,
    data: D,
  ): Promise<R | BusinessErrorResponse> {
    return this.api.patch(url, data);
  }

  public delete<T, D, R = AxiosResponse<T>>(
    url: string,
    data: D,
  ): Promise<R | BusinessErrorResponse> {
    return this.api.patch(url, data);
  }
}
