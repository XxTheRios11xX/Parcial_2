/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TheRi
 */
public class ClienteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
        public Cliente buscar (String dni){
            Cliente c = new Cliente();
            String sql = "select * from cliente where Dni="+dni;
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()){
                    c.setId(rs.getInt(1));
                    c.setDni(rs.getString(2));
                    c.setNom(rs.getString(3));
                    c.setDireccion(rs.getString(4));
                    c.setEstado(rs.getString(5));
                }
            } catch (Exception e) {
                System.out.println("Error al buscar cliente" + e.getMessage());
            }
            return c;
        }
    
     //Operaciones CRUD
    public List listar() {
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setEstado(rs.getString(5));
                lista.add(cl);
            }
        } catch (Exception e) {
            System.out.println("Error al listar" + e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Cliente cl) {
        String sql = "insert into cliente(Dni, Nombres, Direccion, Estado)values(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDireccion());
            ps.setString(4, cl.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al agregar" + e.getMessage());
        }
        return r;
    }
    
    public Cliente listarId(int id){
        Cliente cl= new Cliente();
        String sql = "select * from cliente where IdCliente="+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("Error al listar ID "+ e.getMessage());
        }
        return cl;
    }
    
     public int actualizar(Cliente cl) {
        String sql = "update cliente set Dni=?, Nombres=?, Direccion=?, Estado=? where IdCliente=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDireccion());
            ps.setString(4, cl.getEstado());
            ps.setInt(5, cl.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar" + e.getMessage());
        }
        return r;
    }
     
    public void delete(int id) {
        String sql="delete from cliente where IdCliente="+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al borrar"+e.getMessage());
        }
    }
    
}